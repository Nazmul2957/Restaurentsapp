package com.hrsoftbd.rz.restaurentsapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class SessionManager {

    private SharedPreferences prefs;

    public SessionManager(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setUserId(String usename) {
        prefs.edit().putString("id", usename).commit();
    }

    public String getUserId() {
        return prefs.getString("id", "");
    }


    public void setAppLanguage(String lang) {
        prefs.edit().putString("mlanguage", lang).commit();
    }

    public String getAppLanguage() {
        return prefs.getString("mlanguage", "en");
    }
}