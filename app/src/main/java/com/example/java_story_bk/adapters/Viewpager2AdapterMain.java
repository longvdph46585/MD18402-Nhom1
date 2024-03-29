package com.example.java_story_bk.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.java_story_bk.fragments.AccountFragment;
import com.example.java_story_bk.fragments.DashboardFragment;
import com.example.java_story_bk.fragments.HistoryStoriesFragment;
import com.example.java_story_bk.fragments.LoveStoriesFragment;

public class Viewpager2AdapterMain extends FragmentStateAdapter {
    public Viewpager2AdapterMain(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return  new DashboardFragment();

            case 1: return  new LoveStoriesFragment();

            case 2: return  new HistoryStoriesFragment();

            case 3: return  new AccountFragment();

            default:
                return new DashboardFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
