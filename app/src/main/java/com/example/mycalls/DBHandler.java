package com.example.mycalls;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class DBHandler extends SQLiteOpenHelper {

    //Initializing the table name as CALL_LOG as final
    private static final String CALL_LOG = "CALL_LOG";

    //Constructor for the class
    public DBHandler(Context context) {
        super(context, "callRecord.db", null, 1);
    }

    //When the tables get created
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE " + CALL_LOG + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, PHONE_NUMBER TEXT, " +
                "CALL_TYPE TEXT, CALL_DATE_TIME TEXT, CALL_DURATION INTEGER )");

    }

    //When the table with updated with different version
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS  " + CALL_LOG );
    }

    //Method to insert data into the table
    public boolean insertData(CallEntry callEntry) {
        //Creating a writable database object
        SQLiteDatabase db = this.getWritableDatabase();
        //Using cursor the navigate through the query and to enter the database
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", callEntry.getContactName());
        contentValues.put("PHONE_NUMBER", callEntry.getPhNumber());
        contentValues.put("CALL_TYPE", callEntry.getCallType());
        contentValues.put("CALL_DATE_TIME", callEntry.getCallDateTime());
        contentValues.put("CALL_DURATION", callEntry.getCallDuration());
        long result = db.insert(CALL_LOG, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Method the get data out from the database
    public List<CallEntry> getData(){
        //Array list method called to initiate list
        List<CallEntry> returnList = new ArrayList<>();
        //SQL query
        String queryString = "SELECT * , COALESCE (NAME,'Unknown') AS NAME FROM " + CALL_LOG;
        //Creating readable database object
        SQLiteDatabase db = this.getReadableDatabase();
        //Using cursor the navigate through the query ot get data
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int callID = cursor.getInt(0);
                String contactName = cursor.getString(6);
                String phNumber = cursor.getString(2);
                String callType = cursor.getString(3);
                String callDateTime = cursor.getString(4);
                int callDuration = cursor.getInt(5);
                //call entry is added to an object then passed to the list
                CallEntry newCallEntry = new CallEntry(contactName, phNumber, callType, callDateTime,callDuration);
                returnList.add(newCallEntry);
            }
            while(cursor.moveToNext());
        }else {

        }
        //Closing the database and the cursor
        cursor.close();
        db.close();
        return returnList;
    }

    //Method to get the data of Incoming calls
    public List<CallEntry> getIncomingCallData(){
        //Array list method called to initiate list
        List<CallEntry> returnList = new ArrayList<>();
        //SQL query
        String queryString = "SELECT * , COALESCE (NAME,'Unknown') AS NAME FROM " + CALL_LOG + "  WHERE CALL_TYPE LIKE 'INCOMING'";
        //Creating readable database object
        SQLiteDatabase db = this.getReadableDatabase();
        //Using cursor the navigate through the query and to enter the database
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int callID = cursor.getInt(0);
                String contactName = cursor.getString(6);
                String phNumber = cursor.getString(2);
                String callType = cursor.getString(3);
                String callDateTime = cursor.getString(4);
                int callDuration = cursor.getInt(5);
                //call entry is added to an object then passed to the list
                CallEntry newCallEntry = new CallEntry(contactName, phNumber, callType, callDateTime,callDuration);
                returnList.add(newCallEntry);
            }
            while(cursor.moveToNext());
        }else {

        }

        //Closing the database and the cursor
        cursor.close();
        db.close();
        return returnList;
    }

    //Method to get the data of Outgoing calls
    public List<CallEntry> getOutgoingCallData(){
        //Array list method called to initiate list
        List<CallEntry> returnList = new ArrayList<>();
        //SQL query
        String queryString = "SELECT * , COALESCE (NAME,'Unknown') AS NAME FROM " + CALL_LOG + "  WHERE CALL_TYPE LIKE 'OUTGOING'";
        //Creating readable database object
        SQLiteDatabase db = this.getReadableDatabase();
        //Using cursor the navigate through the query and to enter the database
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int callID = cursor.getInt(0);
                String contactName = cursor.getString(6);
                String phNumber = cursor.getString(2);
                String callType = cursor.getString(3);
                String callDateTime = cursor.getString(4);
                int callDuration = cursor.getInt(5);
                //call entry is added to an object then passed to the list
                CallEntry newCallEntry = new CallEntry(contactName, phNumber, callType, callDateTime,callDuration);
                returnList.add(newCallEntry);
            }
            while(cursor.moveToNext());
        }else {

        }
        //Closing the database and the cursor
        cursor.close();
        db.close();
        return returnList;
    }

    //Method to get the data of Outgoing calls
    public List<CallEntry> getMissedCallData(){
        //Array list method called to initiate list
        List<CallEntry> returnList = new ArrayList<>();
        //SQL query
        String queryString = "SELECT * , COALESCE (NAME,'Unknown') AS NAME FROM " + CALL_LOG + "  WHERE CALL_TYPE LIKE 'MISSED'";
        //Creating readable database object
        SQLiteDatabase db = this.getReadableDatabase();
        //Using cursor the navigate through the query and to enter the database
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int callID = cursor.getInt(0);
                String contactName = cursor.getString(6);
                String phNumber = cursor.getString(2);
                String callType = cursor.getString(3);
                String callDateTime = cursor.getString(4);
                int callDuration = cursor.getInt(5);

                CallEntry newCallEntry = new CallEntry(contactName, phNumber, callType, callDateTime,callDuration);
                returnList.add(newCallEntry);
            }
            while(cursor.moveToNext());
        }else {

        }
        //Closing the database and the cursor
        cursor.close();
        db.close();
        return returnList;
    }

    //Method to clear all data from the database
    public void deleteAll() {
        //Creating a writable database object
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.delete(CALL_LOG,null,null);
        //SQL query
        DB.execSQL("DELETE FROM " + CALL_LOG);
        //Closing the database
        DB.close();
    }

    //Method to get total call duration
    public String getTotalCallDuration(){
        int totalDuration = 0;
        String queryString = "SELECT * FROM " + CALL_LOG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int callDuration = cursor.getInt(5);
                totalDuration = totalDuration + callDuration;
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return getStringDurationFromInt(totalDuration);
    }

    //Method to get total number of calls
    public int getNumOfIncomingCalls(){
        int totalCalls = 0;
        String queryString = "SELECT * FROM " + CALL_LOG + " WHERE CALL_TYPE LIKE 'INCOMING'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                totalCalls++;
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return totalCalls;
    }

    //Method to get total duration of incoming calls
    public String getIncomingCallDuration(){
        int totalDuration = 0;
        String queryString = "SELECT * FROM " + CALL_LOG + " WHERE CALL_TYPE LIKE 'INCOMING'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int callDuration = cursor.getInt(5);
                totalDuration = totalDuration + callDuration;
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return getStringDurationFromInt(totalDuration);
    }

    //Method to get total number of incoming calls
    public int getNumOfOutgoingCalls(){
        int totalCalls = 0;
        String queryString = "SELECT * FROM " + CALL_LOG + " WHERE CALL_TYPE LIKE 'OUTGOING'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                totalCalls++;
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return totalCalls;
    }

    //Method to get outgoing call duration
    public String getOutgoingCallDuration(){
        int totalDuration = 0;
        String queryString = "SELECT * FROM " + CALL_LOG + " WHERE CALL_TYPE LIKE 'OUTGOING'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int callDuration = cursor.getInt(5);
                totalDuration = totalDuration + callDuration;
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return getStringDurationFromInt(totalDuration);
    }

    //Method to get number of missed calls
    public int getNumOfMissedCalls(){
        int totalCalls = 0;
        String queryString = "SELECT * FROM " + CALL_LOG + " WHERE CALL_TYPE LIKE 'MISSED'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                totalCalls++;
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return totalCalls;
    }

    //Method to get highest number of calls
    public String getHighestCallerName(){
        String name = null;
        String queryString = "SELECT *, COUNT (PHONE_NUMBER) AS FREQUENT, COALESCE (NAME,'Unknown') AS NAME FROM " + CALL_LOG
                + " GROUP BY NAME ORDER BY FREQUENT DESC LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                name = cursor.getString(7);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return name;
    }

    //Method to get highest caller call count
    public String getHighestCallerCount(){
        String count = null;
        String queryString = "SELECT PHONE_NUMBER, COUNT (PHONE_NUMBER) AS FREQUENT FROM " + CALL_LOG
                + " GROUP BY PHONE_NUMBER ORDER BY FREQUENT DESC LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                count = cursor.getString(1);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return (count+ " Calls");
    }

    //Method to get the longest caller name
    public String getLongestCallerName(){
        String count = null;
        String queryString = "SELECT *, COALESCE (NAME,'Unknown') AS NAME FROM " + CALL_LOG + " ORDER BY CAST(CALL_DURATION AS INT) DESC LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                count = cursor.getString(6);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return (count);
    }

    //Method to get the longest caller call duration
    public String getLongestCallDuration(){
        int duration = 0;
        String queryString = "SELECT * FROM " + CALL_LOG + " ORDER BY CAST(CALL_DURATION AS INT) DESC LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                duration = Integer.parseInt(cursor.getString(5));
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        //Gets the duration in string
        return getStringDurationFromInt(duration);
    }

    //Method to get the average of total call duration
    public String getAverageCallDuration(){
        int count = 0;
        int totalDuration = 0;
        int averageDuration = 0;
        String queryString = "SELECT * FROM " + CALL_LOG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int callDuration = cursor.getInt(5);
                totalDuration = totalDuration + callDuration;
                count++;
            }
            while(cursor.moveToNext());
        }
        averageDuration = (totalDuration/count);
        if(averageDuration >= 60){
            int minutes = (int) Math.floor(averageDuration / 60);
            int seconds = averageDuration - minutes * 60;
            return (minutes + " min " + seconds + " sec");
        }
        cursor.close();
        db.close();
        return (averageDuration+ " sec");
    }

    //Method to get unknown caller count
    public int getUnknownCallerCount(){
        int count = 0;
        String queryString = "SELECT * FROM " + CALL_LOG + " WHERE  NAME IS NULL";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                count++;
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return (count);
    }

    //Method to get the total call duration in Integer
    public int getTotalCallDurationInteger(){
        int totalDuration = 0;
        String queryString = "SELECT * FROM " + CALL_LOG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int callDuration = cursor.getInt(5);
                totalDuration = totalDuration + callDuration;
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return (totalDuration);
    }

    //Method to get the percentage of outgoing call duration
    public float getOutgoingCallDurationPercentage(){
        int totalDuration = 0;
        String queryString = "SELECT * FROM " + CALL_LOG + " WHERE CALL_TYPE LIKE 'OUTGOING'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int callDuration = cursor.getInt(5);
                totalDuration = totalDuration + callDuration;
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        //Gets the float value
        float durationInPercent = (float)(((double)totalDuration) / ((double)getTotalCallDurationInteger()));
        return durationInPercent;
    }

    //Method to get the percentage of incoming call duration
    public float getIncomingCallDurationPercentage(){
        int totalDuration = 0;
        String queryString = "SELECT * FROM " + CALL_LOG + " WHERE CALL_TYPE LIKE 'INCOMING'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int callDuration = cursor.getInt(5);
                totalDuration = totalDuration + callDuration;
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        //Gets the float value
        float durationInPercent = (float)(((double)totalDuration) / ((double)getTotalCallDurationInteger()));
        return durationInPercent;
    }

    //convert duration from integer to string (differentiating days, hours, minute and seconds)
    public String getStringDurationFromInt(int totalDuration){
        String ans;
        int daySec = 86400;
        int hourSec = 3600;
        int minuteSec = 60;

        if(totalDuration >= daySec){
            int days = (int) Math.floor(totalDuration / daySec);
            int hours = (int) Math.floor((totalDuration - days * daySec)/hourSec);
            int minutes = (int) Math.floor((totalDuration  - days * daySec - hours * hourSec)/minuteSec);
            int seconds = totalDuration - days * daySec - hours * hourSec - minutes * minuteSec;

            ans = (days + " day " +hours + " hrs " + minutes + " min " + seconds + " sec");
        }
        else if (totalDuration >= 3600){
            int days = (int) Math.floor(totalDuration / daySec);
            int hours = (int) Math.floor((totalDuration - days * daySec)/hourSec);
            int minutes = (int) Math.floor((totalDuration  - days * daySec - hours * hourSec)/minuteSec);
            int seconds = totalDuration - days * daySec - hours * hourSec - minutes * minuteSec;

            ans = (hours + " hrs " + minutes + " min " + seconds + " sec");
        }else if(totalDuration >= 60){
            int minutes = (int) Math.floor(totalDuration / 60);
            int seconds = seconds = totalDuration - minutes * 60;
            ans = (minutes + " min " + seconds + " sec");
        } else{
            ans = (totalDuration+ "sec");
        }

        return ans;
    }

}