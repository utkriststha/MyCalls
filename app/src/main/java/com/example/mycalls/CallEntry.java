package com.example.mycalls;

import java.util.Comparator;

public class CallEntry {
    //Initiating private datatype for the variables
    private int id;
    private String contactName;
    private String phNumber;
    private String callType;
    private String callDateTime;
    private int callDuration;

    //Creating a constructor
    public CallEntry(String contactName, String phNumber, String callType, String callDateTime, int callDuration) {
        this.contactName = contactName;
        this.phNumber = phNumber;
        this.callType = callType;
        this.callDateTime = callDateTime;
        this.callDuration = callDuration;
    }

    //Method to convert the object data into string
    @Override
    public String toString() {

        return "CallEntry{" +
                "id=" + id +
                ", contactName='" + contactName + '\'' +
                ", phNumber='" + phNumber + '\'' +
                ", callType='" + callType + '\'' +
                ", callDateTime='" + callDateTime + '\'' +
                ", callDuration='" + callDuration + '\'' +
                '}';
    }

    //Getter and Setter method for the method variables.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getCallDateTime() {
        return callDateTime;
    }

    public void setCallDateTime(String callDateTime) {
        this.callDateTime = callDateTime;
    }

    public int getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(int callDuration) {
        this.callDuration = callDuration;
    }
}
