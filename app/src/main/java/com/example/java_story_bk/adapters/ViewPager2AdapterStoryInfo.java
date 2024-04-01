package com.example.java_story_bk.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.example.java_story_bk.fragments.storyInfo.StoryInfo_about_fragment;
import com.example.java_story_bk.fragments.storyInfo.StoryInfo_chapters_fragment;
import com.example.java_story_bk.fragments.storyInfo.StoryInfo_comments_fragment;
import com.example.java_story_bk.fragments.storyInfo.StoryInfo_reviews_fragment;
import com.example.java_story_bk.models.StoryInfo;


public class ViewPager2AdapterStoryInfo extends FragmentStateAdapter {
    private StoryInfo storyInfo;


    public ViewPager2AdapterStoryInfo(@NonNull FragmentActivity fragmentActivity, StoryInfo storyInfo) {
        super(fragmentActivity);
        this.storyInfo  =storyInfo;

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return  new StoryInfo_about_fragment(storyInfo);

            case 1: return  new StoryInfo_reviews_fragment();
            case 2: return  new StoryInfo_comments_fragment();
            case 3: return  new StoryInfo_chapters_fragment();

            //            case 1: return  new LoveStoriesFragment();
//
//            case 2: return  new HistoryStoriesFragment();
//
//            case 3: return  new AccountFragment();

            default:
                return new StoryInfo_about_fragment(storyInfo);
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
