package com.example.mycalls;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Collections;
import java.util.List;

public class CallLog extends AppCompatActivity {
    //Initiating menu, bottom navigation menu, and database handler
    DBHandler dbHandler;
    Menu menu;
    BottomNavigationView bottomNavigationView;

    //Initiating recycler view
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    //OnCreate method (Operates when application starts)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Assigning the layout
        setContentView(R.layout.activity_call_log);

        //Defining the database handler
        dbHandler = new DBHandler(CallLog.this);
        //Creating list of all call entries on the database
        List<CallEntry> allCallEntries = dbHandler.getData();
        //Reversing the list so that the latest data will come first
        Collections.reverse(allCallEntries);

        //Assigning the recycler view with the id
        recyclerView = findViewById(R.id.lvCallLog);
        recyclerView.setHasFixedSize(true);
        //Setting the layout manger of the recycler view
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //Setting the adapter for the recycle view
        mAdapter = new RecyclerViewAdapter(allCallEntries,CallLog.this);
        recyclerView.setAdapter(mAdapter);


        //Assigning the bottom navigation bar with its id
        bottomNavigationView = findViewById(R.id.menuBotNavBar);
        //setting teh default menu item
        bottomNavigationView.setSelectedItemId(R.id.menuAll);

        //Setting onclick listener for navigation menu bar
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            //Setting onclick listener for item in the navigation menu bar
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Defining the database handler
                dbHandler = new DBHandler(CallLog.this);

                //if the selected item is All Calls
                if(item.getItemId() == R.id.menuAll){
                    //Creating list of all call entries on the database
                    List<CallEntry> allCallEntries = dbHandler.getData();
                    Collections.reverse(allCallEntries);

                    recyclerView = findViewById(R.id.lvCallLog);

                    recyclerView.setHasFixedSize(true);

                    layoutManager = new LinearLayoutManager(CallLog.this);
                    recyclerView.setLayoutManager(layoutManager);

                    mAdapter = new RecyclerViewAdapter(allCallEntries,CallLog.this);
                    recyclerView.setAdapter(mAdapter);
                    return true;
                }
                //if the selected item is Incoming Calls
                else if(item.getItemId() == R.id.menuIncoming){
                    //Creating list of incoming call entries on the database
                    List<CallEntry> allCallEntries = dbHandler.getIncomingCallData();
                    Collections.reverse(allCallEntries);

                    recyclerView = findViewById(R.id.lvCallLog);

                    recyclerView.setHasFixedSize(true);

                    layoutManager = new LinearLayoutManager(CallLog.this);
                    recyclerView.setLayoutManager(layoutManager);

                    mAdapter = new RecyclerViewAdapter(allCallEntries,CallLog.this);
                    recyclerView.setAdapter(mAdapter);
                    return true;
                }
                ///if the selected item is Missed Calls
                else if (item.getItemId() == R.id.menuMissed){
                    //Creating list of missed call entries on the database
                    List<CallEntry> allCallEntries = dbHandler.getMissedCallData();
                    Collections.reverse(allCallEntries);

                    recyclerView = findViewById(R.id.lvCallLog);

                    recyclerView.setHasFixedSize(true);

                    layoutManager = new LinearLayoutManager(CallLog.this);
                    recyclerView.setLayoutManager(layoutManager);

                    mAdapter = new RecyclerViewAdapter(allCallEntries,CallLog.this);
                    recyclerView.setAdapter(mAdapter);
                    return true;
                }
                //if the selected item is Outgoing Calls
                else if (item.getItemId() == R.id.menuOutgoing){
                    //Creating list of outgoing calls entries on the database
                    List<CallEntry> allCallEntries = dbHandler.getOutgoingCallData();
                    Collections.reverse(allCallEntries);

                    recyclerView = findViewById(R.id.lvCallLog);

                    recyclerView.setHasFixedSize(true);

                    layoutManager = new LinearLayoutManager(CallLog.this);
                    recyclerView.setLayoutManager(layoutManager);

                    mAdapter = new RecyclerViewAdapter(allCallEntries,CallLog.this);
                    recyclerView.setAdapter(mAdapter);
                    return true;
                }
                return false;
            }
        });
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        dbHandler = new DBHandler(CallLog.this);

        if(item.getItemId() == R.id.menuAll){
            List<CallEntry> allCallEntries = dbHandler.getData();
            Collections.reverse(allCallEntries);

            recyclerView = findViewById(R.id.lvCallLog);

            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            mAdapter = new RecyclerViewAdapter(allCallEntries,CallLog.this);
            recyclerView.setAdapter(mAdapter);
            return true;
        }else if(item.getItemId() == R.id.menuIncoming){
            List<CallEntry> allCallEntries = dbHandler.getIncomingCallData();
            Collections.reverse(allCallEntries);

            recyclerView = findViewById(R.id.lvCallLog);

            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            mAdapter = new RecyclerViewAdapter(allCallEntries,CallLog.this);
            recyclerView.setAdapter(mAdapter);
            return true;
        } else if (item.getItemId() == R.id.menuMissed){
            List<CallEntry> allCallEntries = dbHandler.getMissedCallData();
            Collections.reverse(allCallEntries);

            recyclerView = findViewById(R.id.lvCallLog);

            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            mAdapter = new RecyclerViewAdapter(allCallEntries,CallLog.this);
            recyclerView.setAdapter(mAdapter);
            return true;
        } else if (item.getItemId() == R.id.menuOutgoing){
            List<CallEntry> allCallEntries = dbHandler.getOutgoingCallData();
            Collections.reverse(allCallEntries);

            recyclerView = findViewById(R.id.lvCallLog);

            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            mAdapter = new RecyclerViewAdapter(allCallEntries,CallLog.this);
            recyclerView.setAdapter(mAdapter);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
    */
}