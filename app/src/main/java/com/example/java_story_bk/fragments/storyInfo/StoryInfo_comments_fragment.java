package com.example.java_story_bk.fragments.storyInfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.example.java_story_bk.R;
import com.example.java_story_bk.models.StoryInfo;


public class StoryInfo_comments_fragment extends Fragment {

    private StoryInfo storyInfo;

    public StoryInfo_comments_fragment(StoryInfo storyInfo) {
        this.storyInfo = storyInfo;
    }

    private ScrollView scrollWrapperStoryInfo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_story_info_comments_fragment, container, false);
    }
}