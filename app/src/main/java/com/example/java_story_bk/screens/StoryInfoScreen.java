package com.example.java_story_bk.screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.java_story_bk.R;
import com.example.java_story_bk.adapters.ViewPager2AdapterStoryInfo;
import com.example.java_story_bk.models.StoryInfo;
import com.example.java_story_bk.services.AccountService;
import com.example.java_story_bk.services.ReadingService;
import com.example.java_story_bk.untils.Helpers;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.ViewPagerOnTabSelectedListener;

public class StoryInfoScreen extends AppCompatActivity {
    StoryInfo storyInfo;
    private ViewPager2 mViewPager2;
    private TabLayout tab_layout_storyInfo;
    private ScrollView scrollWrapperStoryInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_story_info_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mViewPager2 = findViewById(R.id.viewPer2StoryInfo);
        tab_layout_storyInfo = findViewById(R.id.tab_layout_storyInfo);

        initDataFirstScreen();
        initReadingHistory();
    }
    private  void initDataFirstScreen () {
        Intent intent = getIntent();
         storyInfo = (StoryInfo) intent.getSerializableExtra("story_info");
        ImageView  imageStory= findViewById(R.id.storyInfo_image);

                Glide
                .with(this)
                .load(storyInfo.getStory_picture())
                .centerCrop()
                .into(imageStory);

        TextView nameStory = findViewById(R.id.storyInfo_name);
        TextView authorStory = findViewById(R.id.storyInfo_author);
        TextView storyInfo_genres = findViewById(R.id.storyInfo_genres);

        nameStory.setText(storyInfo.getStory_name().toString());
        authorStory.setText("Tác giả: " +storyInfo.getAuhtor_name().toString());
        storyInfo_genres.setText(storyInfo.getStory_genre().toString());
        findViewById(R.id.storyInfoHeaderTop_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initTabLayout();
        initViewPager2();
    }
    private void initViewPager2 () {
        ViewPager2AdapterStoryInfo viewpager2AdapterMain = new ViewPager2AdapterStoryInfo(this, storyInfo);
        mViewPager2.setAdapter(viewpager2AdapterMain);
        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                tab_layout_storyInfo.selectTab(tab_layout_storyInfo.getTabAt(position));

                System.out.println(position);
            }
        });
    }
    private void initTabLayout () {
        tab_layout_storyInfo.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Xử lý khi tab được chọn
                int position = tab.getPosition();
                mViewPager2.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
    }
    private void initReadingHistory () {
        SharedPreferences READING_CURRENT =  this.getSharedPreferences("READING_CURRENT", MODE_PRIVATE);

//
        if(READING_CURRENT.getString ("story_id","").equals(storyInfo.get_id())) {


        } else  {
            AccountService accountService = new AccountService(this);
            if(accountService.checkLoginAccount()) {
                // update in server

            } else {
                // update in local
                ReadingService readingHistoryService =  new ReadingService(this);
                readingHistoryService.insertReadingHistory(storyInfo.get_id(),this);

            }

        }
        READING_CURRENT.edit().putString("story_id", storyInfo.get_id()).apply();


    }
}