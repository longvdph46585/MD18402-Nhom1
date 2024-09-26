package com.example.java_story_bk;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.java_story_bk.adapters.Viewpager2AdapterMain;
import com.example.java_story_bk.services.AccountService;
import com.example.java_story_bk.services.MainServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity1 extends AppCompatActivity {
    final int dashboard = 1;
    final int follow_stories_page = 2;
    final int history_read = 3;
    final int account = 4;
    private ViewPager2 mViewPager2;
    private AccountService accountService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        accountService = new AccountService(MainActivity1.this);
        mViewPager2 = findViewById(R.id.viewPager2Main);
        Viewpager2AdapterMain viewpager2AdapterMain = new Viewpager2AdapterMain(this);
        mViewPager2.setAdapter(viewpager2AdapterMain);


        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
        if (accountService.checkLoginAccount()) {
            MainServices.storyService.getCountNewChapterStories(accountService.getAccountID()).enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful()) {
                        if (response.body() > 0) {
//                            bottomNavigation.setCount(follow_stories_page, response.body().toString());

                        }
                    }


                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {

                }
            });
        }



    }


   
    private void handleChangeItemNavigation(int pageIndex, ViewPager2 mViewPager2) {
        mViewPager2.setCurrentItem(pageIndex);
    }


}