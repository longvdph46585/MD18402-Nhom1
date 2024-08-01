package com.example.java_story_bk.services;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.java_story_bk.DB.DbHelper;
import com.example.java_story_bk.models.ReadingInfoOfUser;
import com.example.java_story_bk.models.StoryInfo;
import com.example.java_story_bk.models.StoryInfoWithIdChapterUpdate;
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
        final ReadingInfoOfUser dataSend = new ReadingInfoOfUser(storyInfo.get_id(), Helpers.getDeviceUUID(ctx), chapter_id, accountService.getAccountID());
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


    public void getAllReading(String device_uuid, int page, int limit, callBackGetStoriesReadHistory callback) {
        ArrayList<StoryInfo> listData = new ArrayList<>();

        Call<ArrayList<StoryInfo>> call = MainServices.storyService.getReadingHistoryForBooks(device_uuid, page, limit, accountService.getAccountID()
        );
        call.enqueue(new Callback<ArrayList<StoryInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<StoryInfo>> call, Response<ArrayList<StoryInfo>> response) {
                listData.addAll(response.body());
                callback.onComplete(listData);

            }

            @Override
            public void onFailure(Call<ArrayList<StoryInfo>> call, Throwable t) {
            }
        });


    }

    public void getFollowedStories(String user_id, int page, int limit, callBackGetFollowedStories callback) {
        ArrayList<StoryInfoWithIdChapterUpdate> listData = new ArrayList<>();

        Call<ArrayList<StoryInfoWithIdChapterUpdate>> call = MainServices.storyService.getFollowedStories(user_id, page, limit);
        call.enqueue(new Callback<ArrayList<StoryInfoWithIdChapterUpdate>>() {
            @Override
            public void onResponse(Call<ArrayList<StoryInfoWithIdChapterUpdate>> call, Response<ArrayList<StoryInfoWithIdChapterUpdate>> response) {

        System.out.println("fsdafsadfsadf");
                listData.addAll(response.body());
                callback.onComplete(listData);

            }

            @Override
            public void onFailure(Call<ArrayList<StoryInfoWithIdChapterUpdate>> call, Throwable t) {
            }
        });


    }

    public interface callBackGetStoriesReadHistory {
        void onComplete(ArrayList<StoryInfo> listData);
    }

    public interface callBackGetFollowedStories {
        void onComplete(ArrayList<StoryInfoWithIdChapterUpdate> listData);
    }

}
