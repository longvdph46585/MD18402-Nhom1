package com.example.java_story_bk.fragments.storyInfo;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.java_story_bk.R;
import com.example.java_story_bk.adapters.AdapterChaptersStory;
import com.example.java_story_bk.models.Chapter;
import com.example.java_story_bk.models.ChapterInfo;
import com.example.java_story_bk.models.StoryInfo;
import com.example.java_story_bk.services.MainServices;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoryInfo_chapters_fragment extends Fragment {
    StoryInfo storyInfo;
    ArrayList<Chapter> dataList = new ArrayList<>();
    RecyclerView recyclerView;
    TextView readContinueChapterFragmentLabel,readContinueChapterFragment;
    private ChapterInfo currentChapter;

    int limit = 20;
    int page = 0;
    AdapterChaptersStory adapter;

    public StoryInfo_chapters_fragment(StoryInfo storyInfo, ChapterInfo currentChapter) {

        this.storyInfo =storyInfo;
        this.currentChapter= currentChapter;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_story_info_chapters_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewChapter);

        readContinueChapterFragmentLabel = view.findViewById(R.id.readContinueChapterFragmentLabel);
        readContinueChapterFragment = view.findViewById(R.id.readContinueChapterFragment);
        if(currentChapter != null) {
            readContinueChapterFragmentLabel.setVisibility(View.VISIBLE);
            readContinueChapterFragment.setText(currentChapter.getChapter_name());
        } else {

        }
        initRecyclerView();

        return view;
    }

    void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new AdapterChaptersStory
                (dataList, getContext(), storyInfo);

        recyclerView.setAdapter(adapter);
        LoadMoreStories(adapter);
        initEventLoadMoreRecyvlerView(adapter);
    }

    private void initEventLoadMoreRecyvlerView(AdapterChaptersStory adapter) {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!recyclerView.canScrollVertically(1)) {
                    // Load more data if RecyclerView cannot scroll more vertically (i.e., reached bottom)
                    LoadMoreStories(adapter);
                }
            }
        });
    }

    private void LoadMoreStories(AdapterChaptersStory adapter) {
        MainServices.storyService.getAllChapters(storyInfo.get_id(), page, limit).enqueue(new Callback<ArrayList<Chapter>>() {
            @Override
            public void onResponse(Call<ArrayList<Chapter>> call, Response<ArrayList<Chapter>> response) {
                dataList.addAll(response.body());
                adapter.notifyItemRangeInserted(page * limit, limit);
                page++;

            }

            @Override
            public void onFailure(Call<ArrayList<Chapter>> call, Throwable t) {

            }
        });
    }

}
