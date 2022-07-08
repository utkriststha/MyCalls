package com.example.mycalls;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
//Initiating button, image view, and database handler
    Button btnCallLog, btnCallReport, btnCallAnalysis;
    DBHandler dbHandler;
    ImageView ivInfo;

    //OnCreate method (Operates when application starts)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Assigning the layout
        setContentView(R.layout.activity_main);

        //Defining the database handler
        dbHandler = new DBHandler(MainActivity.this);

        //Assigning the initialized button and imageview with its respective ID
        btnCallAnalysis = findViewById(R.id.btnCallAnalysis);
        btnCallLog = findViewById(R.id.btnCallLog);
        btnCallReport = findViewById(R.id.btnCallReport);
        ivInfo = findViewById(R.id.ivInfo);

        //To check if the app has permission to access the call log of the phone
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED){
            // Requests permission for app to access call log
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_CALL_LOG)){
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_CALL_LOG},1);
            }else{
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_CALL_LOG},1);
            }
        }
        //If the app has permission to access call log.
        else{
            //Method to get call log details
            getCallDetails();
        }

        //Setting onclick listener for info
        ivInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Setting intent so tha the activity can be called with the button is clicked
                Intent intent = new Intent(MainActivity.this,com.example.mycalls.Info.class);
                startActivity(intent);
            }
        });

        //Setting onclick listener for viewing call log
        btnCallLog.setOnClickListener(view -> {
            //Setting intent so tha the activity can be called with the button is clicked
            Intent intent = new Intent(MainActivity.this, CallLog.class);
            startActivity(intent);
        });

        //Setting onclick listener for Call analysis button
        btnCallAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Setting intent so tha the activity can be called with the button is clicked
                Intent intent = new Intent(MainActivity.this,com.example.mycalls.CallAnalysis.class);
                startActivity(intent);
            }
        });

        //Setting onclick listener for call report
        btnCallReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Setting intent so tha the activity can be called with the button is clicked
                Intent intent = new Intent(MainActivity.this,com.example.mycalls.CallReport.class);
                startActivity(intent);
            }
        });
    }

    //This method let user confirm that the app can access the call log
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //if the user gives permission the app will toast message saying permission granted and if the access is denied the app will display a message saying assess denied
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Permission Granted!!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Permission Denied!!", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
            }
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestCode);
        }

    }

    //Method to get call log detail from the phone call log database
    private void getCallDetails(){

        //Method to clear the application data before entering a new one.
        dbHandler.deleteAll();


//        StringBuffer stringBuffer = new StringBuffer();

        //Cresting a cursor to get throughout the call log query.
        Cursor managedCursor = getContentResolver().query(android.provider.CallLog.Calls.CONTENT_URI,null,null,null,null,null);

        //Getting the cursor index of the call log data
        int name = managedCursor.getColumnIndex(android.provider.CallLog.Calls.CACHED_NAME);
        int number = managedCursor.getColumnIndex(android.provider.CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(android.provider.CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(android.provider.CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(android.provider.CallLog.Calls.DURATION);
//        stringBuffer.append("\n");

        //Creating a loop until the end of call log data of the device
        while(managedCursor.moveToNext()){
            //Getting String and Integer data of the assigned call log data
            String contactName = managedCursor.getString(name);
            String phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy HH:mm");
            String dateString = formatter.format(callDayTime);
            int callDuration = Integer.parseInt(managedCursor.getString(duration));
            String dir = null;
            int dirCode = Integer.parseInt(callType);
            switch (dirCode){
                case (android.provider.CallLog.Calls.OUTGOING_TYPE):
                    dir = "OUTGOING";
                    break;
                case (android.provider.CallLog.Calls.INCOMING_TYPE):
                    dir = "INCOMING";
                    break;
                case (android.provider.CallLog.Calls.MISSED_TYPE):
                    dir = "MISSED";
                    break;
            }

            //Creating an call entry object
            CallEntry callEntry;

            //Loading the call log data into the object
            try {
                callEntry = new CallEntry(contactName,phNumber,dir,dateString,callDuration);
                Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                callEntry = new CallEntry("uk","90900","a","a",1);
            }

            //Loading the call entry object into the database
            dbHandler.insertData(callEntry);
        }

        //Closing the cursor for another use
        managedCursor.close();
    }
}