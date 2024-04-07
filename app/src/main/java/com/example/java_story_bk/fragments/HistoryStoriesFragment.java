package com.example.java_story_bk.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.java_story_bk.R;
import com.example.java_story_bk.models.StoryInfo;
import com.example.java_story_bk.models.bodyModel.SendListStoriesIdBody;
import com.example.java_story_bk.services.MainServices;
import com.example.java_story_bk.services.ReadingService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HistoryStoriesFragment extends Fragment {

    private ReadingService readingService;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("fragment", "HistoryStoriesFragment first load");

    }
    @Override
    public void onResume() {
        super.onResume();
        Log.e("fragment", "HistoryStoriesFragment reload");

    }
    private void getData () {
        //check
         ArrayList<String> listId= new ArrayList<String>();
         listId.add("660064e928338dfc0b6a6686");
        MainServices.storyService.getListStoriesFromId( new SendListStoriesIdBody(listId)).enqueue(new Callback<ArrayList<StoryInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<StoryInfo>> call, Response<ArrayList<StoryInfo>> response) {
                ArrayList<StoryInfo> data = response.body();
                System.out.println(data);
            }

            @Override
            public void onFailure(Call<ArrayList<StoryInfo>> call, Throwable t) {
                System.out.println(t);
                System.out.println("wrdas");


            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("fragment", "HistoryStoriesFragment first CREATE VIEW");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_stories, container, false);
    }
}