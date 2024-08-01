package com.example.java_story_bk.screens;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.java_story_bk.R;
import com.example.java_story_bk.adapters.ViewPager2AdapterStoryInfo;
import com.example.java_story_bk.models.Chapter;
import com.example.java_story_bk.models.ChapterInfo;
import com.example.java_story_bk.models.StoryInfo;
import com.example.java_story_bk.models.UserFollowedStory;
import com.example.java_story_bk.models.bodyModel.SendUpdateFollowStoryBody;
import com.example.java_story_bk.retrofit.api.AccountServices;
import com.example.java_story_bk.services.AccountService;
import com.example.java_story_bk.services.MainServices;
import com.example.java_story_bk.services.ReadingService;
import com.example.java_story_bk.untils.Helpers;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.ViewPagerOnTabSelectedListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoryInfoScreen extends AppCompatActivity {
    StoryInfo storyInfo;
    private ViewPager2 mViewPager2;
    private TabLayout tab_layout_storyInfo;
    private ScrollView scrollWrapperStoryInfo;
    private AccountService accountService;
    private UserFollowedStory userFollowedStory;
    private Button btnRead;
    private ChapterInfo currentChapter;
    ImageView storyInfoHeaderTop_add;

    @SuppressLint("MissingInflatedId")
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
        accountService = new AccountService(this);
        mViewPager2 = findViewById(R.id.viewPer2StoryInfo);
        tab_layout_storyInfo = findViewById(R.id.tab_layout_storyInfo);
        btnRead = findViewById(R.id.btnRead);
        initDataFirstScreen();
        initOtherUi();
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentChapter != null) {
                    Intent intent = new Intent(StoryInfoScreen.this, ReadChapterInStory.class);
                    intent.putExtra("id_chapter", currentChapter.get_id()); // Dữ liệu cần truyền
                    intent.putExtra("story_info", storyInfo);
                    startActivity(intent);
                } else {
                    tab_layout_storyInfo.selectTab(tab_layout_storyInfo.getTabAt(3));
                }
            }
        });
    }

    private void initDataFirstScreen() {
        Intent intent = getIntent();
        storyInfo = (StoryInfo) intent.getSerializableExtra("story_info");
        ImageView imageStory = findViewById(R.id.storyInfo_image);

        Glide
                .with(this)
                .load(storyInfo.getStory_picture())
                .centerCrop()
                .into(imageStory);

        TextView nameStory = findViewById(R.id.storyInfo_name);
        TextView authorStory = findViewById(R.id.storyInfo_author);
        TextView storyInfo_genres = findViewById(R.id.storyInfo_genres);

        nameStory.setText(storyInfo.getStory_name().toString());
        authorStory.setText("Tác giả: " + storyInfo.getAuhtor_name().toString());
        storyInfo_genres.setText(storyInfo.getStory_genre().toString());
        findViewById(R.id.storyInfoHeaderTop_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initTabLayout();
        handleGetInfoFollowed(false);
    }

    @Override
    protected void onResume() {
        MainServices.storyService.getStoryById(storyInfo.get_id()).enqueue(new Callback<StoryInfo>() {
            @Override
            public void onResponse(Call<StoryInfo> call, Response<StoryInfo> response) {
                storyInfo = response.body();
                handleGetCurrentChapter();
            }

            @Override
            public void onFailure(Call<StoryInfo> call, Throwable t) {

            }
        });

        super.onResume();
    }

    private void changeIconToClose(boolean isClose) {
        if (isClose) {
            storyInfoHeaderTop_add.setImageResource(R.drawable.baseline_close_24);
        } else {
            storyInfoHeaderTop_add.setImageResource(R.drawable.add_icon_incre);
        }
    }

    private void handleGetInfoFollowed(boolean isOffChangeIcon ) {
        if (accountService.checkLoginAccount()) {
            System.out.println("ádfasfasdfasdf");
            MainServices.storyService.getFollowedStoryInfo(accountService.getAccountID(), storyInfo.get_id()).enqueue(new Callback<UserFollowedStory>() {
                @Override
                public void onResponse(Call<UserFollowedStory> call, Response<UserFollowedStory> response) {
                    if (response.isSuccessful()) {
                        final UserFollowedStory data = response.body();
                        userFollowedStory = data;
                        if(!isOffChangeIcon) {
                            changeIconToClose(true);

                        }
                    } else {
                        if(!isOffChangeIcon) {
                            changeIconToClose(false);

                        }

                    }
                }

                @Override
                public void onFailure(Call<UserFollowedStory> call, Throwable t) {

                }
            });

        }
    }

    private void handleGetCurrentChapter() {
        MainServices.storyService.getCurrentChapterInStory(Helpers.getDeviceUUID(this), storyInfo.get_id(), accountService.getAccountID()).enqueue(new Callback<ChapterInfo>() {
            @Override
            public void onResponse(Call<ChapterInfo> call, Response<ChapterInfo> response) {
                if (response.isSuccessful()) {
                    currentChapter = response.body();
                    btnRead.setText("Đọc tiếp");

                }
                initViewPager2();
            }

            @Override
            public void onFailure(Call<ChapterInfo> call, Throwable t) {
//                initViewPager2();
            }
        });
    }

    private void initViewPager2() {
        ViewPager2AdapterStoryInfo viewpager2AdapterMain = new ViewPager2AdapterStoryInfo(this, storyInfo, currentChapter);
        mViewPager2.setAdapter(viewpager2AdapterMain);
        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                tab_layout_storyInfo.selectTab(tab_layout_storyInfo.getTabAt(position));

            }
        });
    }

    private void initTabLayout() {
        tab_layout_storyInfo.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

    private void initOtherUi() {
        storyInfoHeaderTop_add = findViewById(R.id.storyInfoHeaderTop_add);
        storyInfoHeaderTop_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accountService.checkLoginAccount()) {
                    boolean isAdd = userFollowedStory == null;

                    MainServices.storyService.updateFollowStory(new SendUpdateFollowStoryBody(accountService.getAccountID(), storyInfo.get_id(), isAdd)).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            boolean isAdd = userFollowedStory == null;
                            System.out.println("ádfasfasdfasdf222");

                            handleGetInfoFollowed(true);
                                changeIconToClose(isAdd);
                            Toast.makeText(StoryInfoScreen.this, isAdd ? "Đã thêm truyện vào danh sách yêu thích" : "Đã loại bỏ truyện khỏi danh sách yêu thích", Toast.LENGTH_LONG).show();
                            if(isAdd == false) {
                                userFollowedStory = null;
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                        System.out.println("heehh");
                        System.out.println(t.getMessage());
                        }
                    });


                } else {
                    showLoginDialog();
                }
            }
        });

    }

    private void showLoginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Đăng nhập");
        builder.setMessage("Vui lòng đăng nhập để tiếp tục");

        builder.setPositiveButton("Đăng nhập", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng chọn "Oke"
                Intent intent = new Intent(StoryInfoScreen.this, LoginActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Để sau", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng chọn "Để sau"

                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}