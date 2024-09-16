package com.example.java_story_bk.fragments.storyInfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.java_story_bk.R;
import com.example.java_story_bk.models.StoryInfo;


public class StoryInfo_about_fragment extends Fragment {
    private StoryInfo storyInfo;
    public StoryInfo_about_fragment (StoryInfo storyInfo ) {
        this.storyInfo= storyInfo;

    }
    TextView storyInfo_about_countFollowers, storyInfo_about_countChapters, storyInfo_about_countViews, storyInfo_about_quickReview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_story_info_about_fragment, container, false);

        storyInfo_about_countFollowers = view.findViewById(R.id.storyInfo_about_countFollowers);
        storyInfo_about_countChapters = view.findViewById(R.id.storyInfo_about_countChapters);
        storyInfo_about_countViews = view.findViewById(R.id.storyInfo_about_countViews);
        storyInfo_about_quickReview = view.findViewById(R.id.storyInfo_about_quickReview);
        initFistSee();
        return view;
    }
    private  void initFistSee () {

            System.out.println(storyInfo.getStory_name());

            storyInfo_about_countFollowers.setText(storyInfo.getCount_followers_story() + "");
            storyInfo_about_countChapters.setText(storyInfo.getCount_chapters() + "");

            storyInfo_about_quickReview.setText(storyInfo.getStory_quick_review() + "");

            // Sử dụng dữ liệu nhận được ở đây
    }
}