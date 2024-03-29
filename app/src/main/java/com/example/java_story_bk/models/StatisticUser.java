package com.example.java_story_bk.models;

import java.util.ArrayList;
public class StatisticUser {
    public StatisticUser(ArrayList<StoryInfo> story_top_ten_of_week, ArrayList<StoryInfo> story_top_ten_followers, ArrayList<StoryInfo> story_top_ten_money) {
        this.story_top_ten_of_week = story_top_ten_of_week;
        this.story_top_ten_followers = story_top_ten_followers;
        this.story_top_ten_money = story_top_ten_money;
    }

    public ArrayList<StoryInfo> getStory_top_ten_of_week() {
        return story_top_ten_of_week;
    }

    public ArrayList<StoryInfo> getStory_top_ten_followers() {
        return story_top_ten_followers;
    }

    public ArrayList<StoryInfo> getStory_top_ten_money() {
        return story_top_ten_money;
    }

    private ArrayList<StoryInfo> story_top_ten_of_week;
    private ArrayList<StoryInfo> story_top_ten_followers;
    private ArrayList<StoryInfo> story_top_ten_money;
}
