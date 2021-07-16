package com.example.fingerpushapp;

public class InfoItem {

    String AppKey;
    String AppName;

    public InfoItem(String AppKey, String AppName) {
        this.AppKey = AppKey;
        this.AppName = AppName;
    }

    public String getAppKey() {
        return AppKey;
    }

    public void setAppKey(String appKey) {
        AppKey = appKey;
    }

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String appName) {
        AppName = appName;
    }
}
