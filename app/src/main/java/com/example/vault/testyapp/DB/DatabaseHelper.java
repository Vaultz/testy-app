package com.example.vault.testyapp.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vault on 17/03/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "database.db";
    static final int DB_VERSION = 1;

    private static DatabaseHelper sInstance;

    public static DatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context);
        }
        return sInstance;
    }

    private DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ARTICLE_TABLE =
                "CREATE TABLE table (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "title TEXT," +
                        "content TEXT, "+
                        "date TEXT"+
                        ");";

        db.execSQL(CREATE_ARTICLE_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // gérer ce cas
    }
}