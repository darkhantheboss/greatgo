package com.s.s.siliconsteppe.data;

import android.app.Application;
import android.content.Context;

//class for context activity for DB access
public class CustomApplication extends Application {
    private static Context context;

    public static Context getCustomAppContext() {
        return context;
    }

    public void onCreate() {
        context = getApplicationContext();
    }
}
