package com.example.java_story_bk.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.java_story_bk.R;

public class LoveStoriesFragment extends Fragment {

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("fragment", "LoveStoriesFragment first load");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("fragment", "LoveStoriesFragment reolad");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_love_stories, container, false);
    }
}