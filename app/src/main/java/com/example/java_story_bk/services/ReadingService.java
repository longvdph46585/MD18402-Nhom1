package com.example.java_story_bk.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.java_story_bk.DB.DbHelper;
import com.example.java_story_bk.models.ReadingHistory;
import com.example.java_story_bk.untils.Helpers;

import java.util.ArrayList;

public class ReadingService {
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public ReadingService(Context context) {
        this.dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public  long insertReadingHistory (String story_id, Context context) {
        ContentValues values = new ContentValues();

        values.put("story_id",story_id);
    return db.insert(DbHelper.TB_reading,null,values);
    }
    public ArrayList<ReadingHistory> getAllReading (int page, int limit) {
        ArrayList<ReadingHistory> list = new ArrayList<>();
        int offset = page  * limit;

        String query = "SELECT * FROM " + DbHelper.TB_reading +
                " ORDER BY " + "create_at" + " DESC" +
                " LIMIT " + limit +
                " OFFSET " + offset;
        Cursor cursor =  db.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                ReadingHistory reading = new ReadingHistory();
                reading.setStory_id(cursor.getColumnName(0));
                reading.setCreate_at(cursor.getColumnName(1));
                list.add(reading);
            }
            while (cursor.moveToNext());
        }
        return list;
    }
}
