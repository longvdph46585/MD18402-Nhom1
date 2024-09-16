package com.example.java_story_bk.services;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.java_story_bk.DB.DbHelper;
import com.example.java_story_bk.models.ReadingInfoOfUser;
import com.example.java_story_bk.models.StoryInfo;
import com.example.java_story_bk.models.bodyModel.SendListStoriesIdBody;
import com.example.java_story_bk.models.bodyModel.SendReadingInfoOfUser;
import com.example.java_story_bk.untils.Helpers;

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


    public void addReadingInfoForUser(Context ctx, StoryInfo storyInfo, String chapter_id) {
        //check
        final ReadingInfoOfUser dataSend = new ReadingInfoOfUser(storyInfo.get_id(), Helpers.getDeviceUUID(ctx), chapter_id, "");
        final SendReadingInfoOfUser bodySend = new SendReadingInfoOfUser(dataSend);
        MainServices.storyService.addReadingInfoOfUser(bodySend).enqueue(new Callback<ReadingInfoOfUser>() {
            @Override
            public void onResponse(Call<ReadingInfoOfUser> call, Response<ReadingInfoOfUser> response) {

            }

            @Override
            public void onFailure(Call<ReadingInfoOfUser> call, Throwable t) {

            }
        });


    }


    public void getAllReading(String device_uuid,int page, int limit, callBackGetStoriesReadHistory callback) {
        ArrayList<StoryInfo> listData = new ArrayList<>();

        Call<ArrayList<StoryInfo>> call = MainServices.storyService.getReadingHistoryForBooks(device_uuid, page,limit
        );
        call.enqueue(new Callback<ArrayList<StoryInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<StoryInfo>> call, Response<ArrayList<StoryInfo>> response) {
                    System.out.println(response.body());
                listData.addAll(response.body());
                callback.onComplete(listData);

            }

            @Override
            public void onFailure(Call<ArrayList<StoryInfo>> call, Throwable t) {
                System.out.println("zoo");
            }
        });


    }

    public interface callBackGetStoriesReadHistory {
        void onComplete(ArrayList<StoryInfo> listData);
    }


}
