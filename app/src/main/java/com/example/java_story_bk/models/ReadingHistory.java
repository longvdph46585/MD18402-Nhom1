package com.example.java_story_bk.models;

public class ReadingHistory {

     private   String create_at;

    public ReadingHistory() {
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public void setStory_id(String story_id) {
        this.story_id = story_id;
    }


    public String getCreate_at() {
        return create_at;
    }

    public String getStory_id() {
        return story_id;
    }


    private  String story_id;
}
