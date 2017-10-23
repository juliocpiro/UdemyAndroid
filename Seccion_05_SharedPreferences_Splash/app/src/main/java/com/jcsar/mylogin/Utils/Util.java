package com.jcsar.mylogin.Utils;

import android.content.SharedPreferences;

public class Util {
    public static String getUserMailPrefs(SharedPreferences preferences) {
        return preferences.getString("email", "");
    }

    public static String getPassMailPrefs(SharedPreferences preferences) {
        return preferences.getString("pass", "");
    }
}
