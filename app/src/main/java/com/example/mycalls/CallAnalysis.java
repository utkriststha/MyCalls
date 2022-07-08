package com.example.mycalls;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.List;

public class CallAnalysis extends AppCompatActivity {


    //Initiating text view, and database handler
    TextView tvResultTotalCallNumber, tvResultTotalCallTime,
            tvResultIncomingCallNumber, tvResultIncomingCallTime,
            tvResultOutgoingCallNumber, tvResultOutgoingCallTime,
            tvResultMissedCallNumber,
            tvResultHighestCallerName, tvResultHighestCallNumber,
            tvResultLongestCallerName, tvResultLongestCallDuration,
            tvResultAverageCall,tvResultUnknownCalls;
    DBHandler dbHandler;


    //OnCreate method (Operates when application starts)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Assigning the layout
        setContentView(R.layout.activity_call_analysis);

        //Assigning the initialized buttons with its respective ID
        //Total Calls
        tvResultTotalCallNumber = findViewById(R.id.tvResultTotalCallNumber);
        tvResultTotalCallTime = findViewById(R.id.tvResultTotalCallTime);

        //Incoming Calls
        tvResultIncomingCallNumber= findViewById(R.id.tvResultIncomingCallNumber);
        tvResultIncomingCallTime = findViewById(R.id.tvResultIncomingCallTime);

        //Outgoing Calls
        tvResultOutgoingCallNumber= findViewById(R.id.tvResultOutgoingCallNumber);
        tvResultOutgoingCallTime = findViewById(R.id.tvResultOutgoingCallTime);

        //Missed Calls
        tvResultMissedCallNumber= findViewById(R.id.tvResultMissedCallNumber);

        //Highest Caller
        tvResultHighestCallerName = findViewById(R.id.tvResultHighestCallerName);
        tvResultHighestCallNumber = findViewById(R.id.tvResultHighestCallNumber);

        //Longest Caller
        tvResultLongestCallerName = findViewById(R.id.tvResultLongestCallerName);
        tvResultLongestCallDuration = findViewById(R.id.tvResultLongestCallDuration);

        //Average Caller
        tvResultAverageCall = findViewById(R.id.tvResultAverageCall);

        //Unknown Caller
        tvResultUnknownCalls = findViewById(R.id.tvResultUnknownCalls);

        //Defining the database handler
        dbHandler = new DBHandler(CallAnalysis.this);

        //Creating list of all call entries on the database
        List<CallEntry> allCallEntries = dbHandler.getData();

        //Setting the text value of the items with the value from database

        //Total Calls
        tvResultTotalCallNumber.setText(String.valueOf(allCallEntries.size()));
        tvResultTotalCallTime.setText(dbHandler.getTotalCallDuration());

        //Incoming Calls
        tvResultIncomingCallNumber.setText(String.valueOf(dbHandler.getNumOfIncomingCalls()));
        tvResultIncomingCallTime.setText(dbHandler.getIncomingCallDuration());

        //Outgoing Calls
        tvResultOutgoingCallNumber.setText(String.valueOf(dbHandler.getNumOfOutgoingCalls()));
        tvResultOutgoingCallTime.setText(dbHandler.getOutgoingCallDuration());

        //Missing Calls
        tvResultMissedCallNumber.setText(String.valueOf(dbHandler.getNumOfMissedCalls()));

        //Highest Caller
        tvResultHighestCallerName.setText(dbHandler.getHighestCallerName());
        tvResultHighestCallNumber.setText(dbHandler.getHighestCallerCount());

        //Longest Caller
        tvResultLongestCallerName.setText(dbHandler.getLongestCallerName());
        tvResultLongestCallDuration.setText(dbHandler.getLongestCallDuration());

        //Average Calls
        tvResultAverageCall.setText(dbHandler.getAverageCallDuration());

        //Unknown Calls
        tvResultUnknownCalls.setText(String.valueOf(dbHandler.getUnknownCallerCount()));
    }

}