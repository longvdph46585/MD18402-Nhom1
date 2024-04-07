package com.example.java_story_bk.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Story";
    private static final int DB_VERSION = 1000;
    public static final String TB_reading = "ReadingStory";


    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        create table
        String createReadingTB = "CREATE TABLE " + TB_reading + "(" +
                "story_id     TEXT NOT NULL ," +
                "create_at" + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

        db.execSQL(createReadingTB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if( oldVersion != newVersion) {
            System.out.println("checkkk");
            String dropTblProd = "DROP TABLE IF EXISTS " + TB_reading;
            db.execSQL(dropTblProd);
            onCreate(db);
        }

    }
}