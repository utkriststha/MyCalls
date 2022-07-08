package com.example.mycalls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CallReport extends AppCompatActivity {
    //Initiating buttons
    Button btnCallDuration, btnNoOfCalls;

    //OnCreate method (Operates when application starts)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Assigning the layout
        setContentView(R.layout.activity_call_report);

        //Assigning the initialized buttons with its respective ID
        btnCallDuration = findViewById(R.id.btnCallDuration);
        btnNoOfCalls = findViewById(R.id.btnNoOfcalls);

        //Setting onclick listener for call duration button
        btnCallDuration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent to start the activity
                Intent intent = new Intent(CallReport.this, PieChartCallDuration.class);
                startActivity(intent);
            }
        });

        //Setting onclick listener for number of calls button
        btnNoOfCalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent to start the activity
                Intent intent = new Intent(CallReport.this, PieChartNoOfCalls.class);
                startActivity(intent);
            }
        });
    }
}