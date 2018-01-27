package com.s.s.siliconsteppe.data.DB;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.s.s.siliconsteppe.data.CustomApplication;

public class DBAccess {

    // Database Name
    public static final String DATABASE_NAME = "00534D";

    //Database Version
    public static final int DATABASE_VERSION = 1;// started at 1

    //Create Chat values
    public static final String CHAT_TB = "chat";
    public static final String CHAT_ID = "chat_id";
    public static final String CHAT_DATA = "chat_data";
    public static final String CHAT_NAME = "chat_name";


    //Create table query
    private static final String CHAT_CREATE =
            "CREATE TABLE chat (chat_id INTEGER PRIMARY KEY AUTOINCREMENT,chat_name TEXT NOT NULL,chat_data TEXT NOT NULL);";


    private static DBConnect dbConnect = null;

    private DBAccess() {
    }


    //Singleton class to create one object of dbaccess
    public static SQLiteDatabase getWritableDatabase() throws SQLiteException {
        if (dbConnect == null) {
            synchronized (DBAccess.class) {
                dbConnect = new DBConnect();
            }
        }
        return dbConnect.getWritableDatabase();
    }

    private static class DBConnect extends SQLiteOpenHelper {//Inner class for create only one database Ob using singleton

        public DBConnect() {
            super(CustomApplication.getCustomAppContext(), DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                //Create tables
                db.execSQL(CHAT_CREATE);

            } catch (Exception exception) {
                Log.i("DatabaseHandler", "Exception onCreate() exception : " + exception.getMessage());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + CHAT_TB); //On upgrade drop tables
            onCreate(db);
        }
    }
}
