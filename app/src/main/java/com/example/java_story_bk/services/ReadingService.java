package com.example.java_story_bk.services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.java_story_bk.DB.DbHelper;
import com.example.java_story_bk.models.ReadingHistoryLocal;
import com.example.java_story_bk.models.StoryInfo;
import com.example.java_story_bk.models.bodyModel.SendListStoriesIdBody;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadingService {
    private DbHelper dbHelper;
    private SQLiteDatabase db;
    private AccountService accountService;

    public ReadingService(Context context) {
        this.dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        accountService = new AccountService(context);
    }

    public long insertReadingHistory(String story_id, Context context) {
        ContentValues values = new ContentValues();

        values.put("story_id", story_id);
        db.delete(DbHelper.TB_reading, "story_id=?",new String[]{story_id});
        return db.insert(DbHelper.TB_reading, null, values);
    }

    public void getAllReading(int page, int limit,callBackGetStoriesReadHistory callback) {
        ArrayList<ReadingHistoryLocal> listReadingHistoryLocal = new ArrayList<>();
        ArrayList<StoryInfo> listData = new ArrayList<>();

        int offset = page * limit;
        System.out.println(offset);

        String query = String.format("SELECT * FROM %s ORDER BY %s DESC LIMIT %d OFFSET %d",
                DbHelper.TB_reading, "create_at", limit, offset);

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                ReadingHistoryLocal reading = new ReadingHistoryLocal();
                reading.setStory_id(cursor.getString(0));
                reading.setCreate_at(cursor.getString(1));
                listReadingHistoryLocal.add(reading);
            }
            while (cursor.moveToNext());
        }
        // kiểm tra xem có data nào dưới local không

        if (!listReadingHistoryLocal.isEmpty()) {
            // nếu như có, thì lấy data đó, get truyện
            ArrayList<String> listId = new ArrayList<>();
            for (ReadingHistoryLocal localStoryId : listReadingHistoryLocal) {
                listId.add(localStoryId.getStory_id());
            }

            Call<ArrayList<StoryInfo>> call = MainServices.storyService.getListStoriesFromId(new SendListStoriesIdBody(listId));
            call.enqueue(new Callback<ArrayList<StoryInfo>>() {
                @Override
                public void onResponse(Call<ArrayList<StoryInfo>> call, Response<ArrayList<StoryInfo>> response) {
                    listData.addAll(response.body());
                    callback.onComplete(listData);

                }

                @Override
                public void onFailure(Call<ArrayList<StoryInfo>> call, Throwable t) {
                    System.out.println("zoo");
                }
            });
        } else {

            // logic get phía server
        }



    }
    public interface callBackGetStoriesReadHistory{
         void onComplete(ArrayList<StoryInfo> listData);
    }



}
