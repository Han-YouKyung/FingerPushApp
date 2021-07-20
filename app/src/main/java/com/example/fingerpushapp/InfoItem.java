package com.example.fingerpushapp;

public class InfoItem {

    String AppName;
    String AppValue;

    public InfoItem(String AppName, String AppValue) {
        this.AppName = AppName;
        this.AppValue = AppValue;
    }

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String AppName) {
        AppName = AppName;
    }

    public String getAppValue() {
        return AppValue;
    }

    public void getAppValue(String AppValue) {
        AppValue = AppValue;
    }
}
