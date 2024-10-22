package com.example.java_story_bk.fragments.storyInfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.example.java_story_bk.R;
import com.example.java_story_bk.models.StoryInfo;

public class StoryInfo_reviews_fragment extends Fragment {

    private StoryInfo storyInfo;
    Button bt;
    EditText ed;
    RecyclerView recy;


    public StoryInfo_reviews_fragment( StoryInfo storyInfo) {
        this.storyInfo = storyInfo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_story_info_reviews_fragment, container, false);
       bt=view.findViewById(R.id.btrv);
       ed=view.findViewById(R.id.edrv);
       recy=view.findViewById(R.id.recyrate);
       bt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String t=ed.getText().toString();

           }
       });
        return view;
    }
}