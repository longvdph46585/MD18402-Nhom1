package com.example.java_story_bk.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.java_story_bk.R;
import com.example.java_story_bk.models.ChapterInfo;
import com.example.java_story_bk.models.StoryInfo;
import com.example.java_story_bk.services.MainServices;
import com.example.java_story_bk.services.ReadingService;
import com.example.java_story_bk.untils.Helpers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadChapterInStory extends AppCompatActivity {
    private TextView chapterName;
    Button prevBtn, nextBtn;
    ChapterInfo chapterInfo;
    ScrollView scrollViewChapter;
    WebView webviewReadChapter;
    StoryInfo storyInfo;
    ReadingService readingService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_read_chapte_in_story);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        readingService= new ReadingService( this);
        prevBtn = findViewById(R.id.prevChapterBtn);
        chapterName = findViewById(R.id.chapterName);
        nextBtn = findViewById(R.id.nextChapterBtn);
        scrollViewChapter = findViewById(R.id.scrollViewChapter);
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainServices.storyService.getPrevChapter(chapterInfo.getStory_id(), chapterInfo.getIndex()).enqueue(new Callback<ChapterInfo>() {

                    @Override
                    public void onResponse(Call<ChapterInfo> call, Response<ChapterInfo> response) {
                        handleUpdateChapter(response.body());

                    }

                    @Override
                    public void onFailure(Call<ChapterInfo> call, Throwable t) {

                    }
                });
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainServices.storyService.getNextChapter(chapterInfo.getStory_id(), chapterInfo.getIndex()).enqueue(new Callback<ChapterInfo>() {
                    @Override
                    public void onResponse(Call<ChapterInfo> call, Response<ChapterInfo> response) {


                        handleUpdateChapter(response.body());
                    }

                    @Override
                    public void onFailure(Call<ChapterInfo> call, Throwable t) {

                    }
                });
            }
        });

        webviewReadChapter = findViewById(R.id.webviewReadChapter);

        webviewReadChapter.setWebViewClient(new WebViewClient()); // Đảm bảo link mở trong WebView, không mở bằng trình duyệt mặc định
        webviewReadChapter.getSettings().setJavaScriptEnabled(true); // Kích hoạt JavaScript (nếu cần)

        Intent intent = getIntent();
        String chaper_id = intent.getStringExtra("id_chapter"); // Lấy dữ liệu
        storyInfo = (StoryInfo) (intent.getSerializableExtra("story_info")); // Lấy dữ liệu


        MainServices.storyService.getChapterInfoById(chaper_id).enqueue(new Callback<ChapterInfo>() {
            @Override
            public void onResponse(Call<ChapterInfo> call, Response<ChapterInfo> response) {

                handleUpdateChapter(response.body());
            }

            @Override
            public void onFailure(Call<ChapterInfo> call, Throwable t) {

            }
        });

    }

    private void handleUpdateChapter(ChapterInfo chapterInfo) {
        // update On database
        readingService.addReadingInfoForUser(this,storyInfo,chapterInfo.get_id());


        handelUpdateVisibleButton(chapterInfo.getIndex(), (storyInfo.getCount_chapters()));

        this.chapterInfo = chapterInfo;
        chapterName.setText(chapterInfo.getChapter_name());
        webviewReadChapter.loadData(Helpers.WrapHtmlContent(chapterInfo.getContent_chapter()), "text/html", "UTF-8");
        scrollViewChapter.smoothScrollTo(0,0);

    }

    private void handelUpdateVisibleButton(int index, int totalChapter) {
        prevBtn.setVisibility(index == 0 ? View.INVISIBLE : View.VISIBLE);
        nextBtn.setVisibility(index + 1 == totalChapter ? View.INVISIBLE : View.VISIBLE);

    }
}