package com.example.java_story_bk.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.java_story_bk.R;
import com.example.java_story_bk.adapters.AdapterListVerticalFollowedStories;
import com.example.java_story_bk.adapters.AdapterListVerticalStories;
import com.example.java_story_bk.models.StoryInfo;
import com.example.java_story_bk.models.StoryInfoWithIdChapterUpdate;
import com.example.java_story_bk.models.UserFollowedStory;
import com.example.java_story_bk.retrofit.api.AccountServices;
import com.example.java_story_bk.services.AccountService;
import com.example.java_story_bk.services.ReadingService;
import com.example.java_story_bk.untils.Helpers;

import java.util.ArrayList;

public class LoveStoriesFragment extends Fragment {
    private RelativeLayout wrapLoveStoriesFragment_login;
    int page = 0, limit = 15;
    private AdapterListVerticalFollowedStories adapter;
    private ArrayList<StoryInfoWithIdChapterUpdate> listData = new ArrayList<>();
    private ReadingService readingService;
    private AccountService accountService;
    private RecyclerView recyclerview;
    private   ReadingService.callBackGetFollowedStories callBackDoneGetStories;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("fragment", "LoveStoriesFragment first load");

    }

    @Override
    public void onResume() {
        super.onResume();
        page = 0;
        limit = 15;
        Log.e("fragment", "LoveStoriesFragment reolad");
        listData.clear();
        adapter.notifyDataSetChanged();
        if(accountService.checkLoginAccount()) {
            wrapLoveStoriesFragment_login.setVisibility(View.INVISIBLE);
            recyclerview.setVisibility(View.VISIBLE);
            getData();

        }
    }
    private  void getData() {
        System.out.println(accountService.getAccountID() + " fsdafsadfsadf" );
        readingService.getFollowedStories(accountService.getAccountID(),page, limit,callBackDoneGetStories);

    }
    private AdapterListVerticalFollowedStories setRecyclerViewShowVertical (ArrayList<StoryInfoWithIdChapterUpdate> data, RecyclerView recyclerViewTop, Context ctx) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ctx,LinearLayoutManager.VERTICAL, false);
        recyclerViewTop.setLayoutManager(linearLayoutManager);

        AdapterListVerticalFollowedStories adapter = new AdapterListVerticalFollowedStories
                (data,ctx);
        recyclerViewTop.setAdapter(adapter);
        return adapter;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        accountService = new AccountService(getContext());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_love_stories, container, false);
        wrapLoveStoriesFragment_login = view.findViewById(R.id.wrapLoveStoriesFragment_login);
        recyclerview = view.findViewById(R.id.listItemLove);
        final Button btn= wrapLoveStoriesFragment_login.findViewById(R.id.wrapLoveStoriesFragment_login_mean_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        readingService = new ReadingService(getContext());
        adapter = setRecyclerViewShowVertical(listData,recyclerview,getContext());
        callBackDoneGetStories = new ReadingService.callBackGetFollowedStories() {
            @Override
            public void onComplete(ArrayList<StoryInfoWithIdChapterUpdate> data) {
                listData.addAll(data);

                adapter.notifyItemRangeInserted(page * limit, limit);
                page++;
            }
        };

//        if(accountService.checkLoginAccount()) {
//            wrapLoveStoriesFragment_login.setVisibility(View.INVISIBLE);
//            recyclerview.setVisibility(View.VISIBLE);
//            getData();
//
//        }


    return  view;
    }
}