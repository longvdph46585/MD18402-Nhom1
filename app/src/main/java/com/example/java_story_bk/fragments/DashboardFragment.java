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
import android.widget.ScrollView;

import com.example.java_story_bk.MainActivity;
import com.example.java_story_bk.R;
import com.example.java_story_bk.adapters.AdapterListVerticalStories;
import com.example.java_story_bk.adapters.AdapterTopStories;
import com.example.java_story_bk.models.StatisticUser;
import com.example.java_story_bk.models.StoryInfo;
import com.example.java_story_bk.services.MainServices;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {
    StatisticUser statisticUser;
    RecyclerView recyclerViewTopOfWeek;
    RecyclerView recyclerview_topStoriesOfFollow;
    RecyclerView recyclerview_topStoriesOfDonate;
    RecyclerView recyclerView_list_stories_el;
    ScrollView listStories;
     private int limit = 5;
     private int page = 0;
     private ArrayList<StoryInfo>  allStories = new ArrayList<>();
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("fragment", "dashboard first load");
    }
    private void setRecyclerViewShowHorizontal (ArrayList<StoryInfo> data, RecyclerView recyclerViewTop) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        recyclerViewTop.setLayoutManager(linearLayoutManager);

        AdapterTopStories adapter = new AdapterTopStories
                (data,getContext());
        recyclerViewTop.setAdapter(adapter);
    }
    private AdapterListVerticalStories setRecyclerViewShowVertical (ArrayList<StoryInfo> data, RecyclerView recyclerViewTop) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        recyclerViewTop.setLayoutManager(linearLayoutManager);

        AdapterListVerticalStories adapter = new AdapterListVerticalStories
                (data,getContext());
        recyclerViewTop.setAdapter(adapter);
        return adapter;
    }
    @Override
    public void onResume() {
        super.onResume();
       MainServices.storyService.getStatisticsOfUser().enqueue(new Callback<StatisticUser>() {
            @Override
            public void onResponse(Call<StatisticUser> call, Response<StatisticUser> response) {
                statisticUser = response.body();
                setRecyclerViewShowHorizontal(statisticUser.getStory_top_ten_of_week(),recyclerViewTopOfWeek);
                setRecyclerViewShowHorizontal(statisticUser.getStory_top_ten_followers(),recyclerview_topStoriesOfFollow);
                setRecyclerViewShowHorizontal(statisticUser.getStory_top_ten_money(),recyclerview_topStoriesOfDonate);

            }

            @Override
            public void onFailure(Call<StatisticUser> call, Throwable t) {

            }
        });
        AdapterListVerticalStories adapter = setRecyclerViewShowVertical(allStories,recyclerView_list_stories_el);

        listStories.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (isScrollAtBottom(v)) {
                    // Load more items

                    LoadMoreStories(adapter);
                }
            }

            private boolean isScrollAtBottom(View view) {
                if (view instanceof ScrollView) {
                    ScrollView scrollView = (ScrollView) view;
                    View child = scrollView.getChildAt(0);
                    return (scrollView.getScrollY() + scrollView.getHeight()) >= child.getHeight();
                }
                return false;
            }
        });
    }
   private void LoadMoreStories (AdapterListVerticalStories adapter) {
       MainServices.storyService.getAllStories(page,limit, "").enqueue(new Callback<ArrayList<StoryInfo>>() {
           @Override
           public void onResponse(Call<ArrayList<StoryInfo>> call, Response<ArrayList<StoryInfo>> response) {

               allStories.addAll(response.body()) ;
               adapter.notifyItemRangeInserted(page *limit,limit);

           }

           @Override
           public void onFailure(Call<ArrayList<StoryInfo>> call, Throwable t) {

           }
       });
        this.page++;
   }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerViewTopOfWeek = view.findViewById(R.id.recyclerview_topStoriesOfWeek);
        recyclerview_topStoriesOfFollow = view.findViewById(R.id.recyclerview_topStoriesOfFollow);
        recyclerview_topStoriesOfDonate = view.findViewById(R.id.recyclerview_topStoriesOfDonate);
        recyclerView_list_stories_el = view.findViewById(R.id.recyclerView_list_stories_el);
        listStories = view.findViewById(R.id.scrollViewDashboard);

        return  view;
    }
}