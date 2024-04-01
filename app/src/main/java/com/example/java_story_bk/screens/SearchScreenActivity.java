package com.example.java_story_bk.screens;

import static java.security.AccessController.getContext;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.java_story_bk.R;
import com.example.java_story_bk.adapters.AdapterListVerticalStories;
import com.example.java_story_bk.models.StoryInfo;
import com.example.java_story_bk.services.MainServices;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchScreenActivity extends AppCompatActivity {

    int page = 5;
    int limit = 5;
    RecyclerView recyclerView ;
    ImageView iConClick ;
    EditText inputSearch;
    ArrayList<StoryInfo> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_screen2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inputSearch = findViewById(R.id.headerSearch_inputSearch);
        iConClick = findViewById(R.id.headerSearch_searchIcon);
        recyclerView =  findViewById(R.id.recyclerViewSearch);
        AdapterListVerticalStories adapter =setRecyclerViewShowVertical(dataList,recyclerView);
//
        iConClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("zooo123123");
                page = 0;
                dataList.clear();
                LoadMoreStories(adapter);
            }
        });
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
    private void LoadMoreStories (AdapterListVerticalStories adapter) {
        MainServices.storyService.getAllStories(page,limit, inputSearch.getText().toString()).enqueue(new Callback<ArrayList<StoryInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<StoryInfo>> call, Response<ArrayList<StoryInfo>> response) {

                dataList.addAll(response.body()) ;
                adapter.notifyDataSetChanged();
                System.out.println("he2h");

            }

            @Override
            public void onFailure(Call<ArrayList<StoryInfo>> call, Throwable t) {
System.out.println("heh");
            }
        });
        this.page++;
    }
    private AdapterListVerticalStories setRecyclerViewShowVertical (ArrayList<StoryInfo> data, RecyclerView recyclerViewTop) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchScreenActivity.this,LinearLayoutManager.VERTICAL, false);
        recyclerViewTop.setLayoutManager(linearLayoutManager);

        AdapterListVerticalStories adapter = new AdapterListVerticalStories
                (data,SearchScreenActivity.this);
        recyclerViewTop.setAdapter(adapter);
        return adapter;
    }
}