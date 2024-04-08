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

import com.example.java_story_bk.R;
import com.example.java_story_bk.adapters.AdapterListVerticalStories;
import com.example.java_story_bk.models.ReadingHistoryLocal;
import com.example.java_story_bk.models.StoryInfo;
import com.example.java_story_bk.screens.SearchScreenActivity;
import com.example.java_story_bk.services.AccountService;
import com.example.java_story_bk.services.MainServices;
import com.example.java_story_bk.services.ReadingService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HistoryStoriesFragment extends Fragment {
    int page = 0, limit = 15;
    RecyclerView recyclerView;

    public HistoryStoriesFragment() {

    }

    private ReadingService readingService;
    private ReadingService.callBackGetStoriesReadHistory callBackDoneGetStories;
    private AccountService accountService;
    private AdapterListVerticalStories adapter;
    private ArrayList<StoryInfo> listReadingStories = new ArrayList<>();

    @Override
    public void onResume() {
        super.onResume();
        Log.e("fragment", "HistoryStoriesFragment reload");
        page = 0;
        limit = 15;
        listReadingStories.clear();
        adapter.notifyDataSetChanged();
        System.out.println(listReadingStories.size() + " size");
        getData();


    }

    private void getData() {
        //check

        readingService.getAllReading(page, limit, callBackDoneGetStories);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("fragment", "HistoryStoriesFragment first CREATE VIEW");
        //
        Context ctx = getContext();
        accountService = new AccountService(ctx);
        readingService = new ReadingService(ctx);
        callBackDoneGetStories = new ReadingService.callBackGetStoriesReadHistory() {
            @Override
            public void onComplete(ArrayList<StoryInfo> listData) {
                listReadingStories.addAll(listData);
                adapter.notifyItemRangeInserted(page * limit, limit);
                page++;
            }
        };
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_stories, container, false);
        recyclerView = view.findViewById(R.id.listItemHistories);
        adapter = setRecyclerViewShowVertical(listReadingStories,recyclerView,ctx);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1) && listReadingStories.size() >= limit) {
                    // Load more data if RecyclerView cannot scroll more vertically (i.e., reached bottom)
                    getData();

                }
            }
        });
        return view;
    }
    private AdapterListVerticalStories setRecyclerViewShowVertical (ArrayList<StoryInfo> data, RecyclerView recyclerViewTop, Context ctx) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ctx,LinearLayoutManager.VERTICAL, false);
        recyclerViewTop.setLayoutManager(linearLayoutManager);

        AdapterListVerticalStories adapter = new AdapterListVerticalStories
                (data,ctx);
        recyclerViewTop.setAdapter(adapter);
        return adapter;
    }
}