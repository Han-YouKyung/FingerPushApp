package com.example.fingerpushapp;

public class NoticeItem {

    String Message;
    String Date;

    public NoticeItem(String message, String date) {
        Message = message;
        Date = date;
    }


    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
