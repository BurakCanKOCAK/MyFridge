package com.bkocak.myfridge.config;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by burakcankocak on 20/05/2017.
 */

public class Config {
    public static final String PREFERENCE_NAME = "PREFERENCE_DATA";
    public static final String KEY_EMAIL = "EMAIL_KEY";
    private final SharedPreferences sharedpreferences;

    public Config(Context context) {
        sharedpreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public String getEMail() {
        String eMail = sharedpreferences.getString(KEY_EMAIL, "");
        return eMail;
    }

    public void setEMail(String eMail) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(KEY_EMAIL, eMail);
        editor.commit();
    }

    public void clearEMail() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove(KEY_EMAIL);
        editor.commit();
    }
}