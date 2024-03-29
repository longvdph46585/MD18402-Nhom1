package com.example.java_story_bk.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.java_story_bk.MainActivity;
import com.example.java_story_bk.R;
import com.example.java_story_bk.models.StatisticUser;
import com.example.java_story_bk.services.MainServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {
    StatisticUser statisticUser;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("fragment", "dashboard first load");
    }
    @Override
    public void onResume() {
        super.onResume();
       MainServices.storyService.getStatisticsOfUser().enqueue(new Callback<StatisticUser>() {
            @Override
            public void onResponse(Call<StatisticUser> call, Response<StatisticUser> response) {
                statisticUser = response.body();
                System.out.println("===>");

                System.out.println(statisticUser.getStory_top_ten_followers().size());
                System.out.println(statisticUser.getStory_top_ten_of_week().size());
                System.out.println(statisticUser.getStory_top_ten_money().size());

            }

            @Override
            public void onFailure(Call<StatisticUser> call, Throwable t) {

            }
        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }
}