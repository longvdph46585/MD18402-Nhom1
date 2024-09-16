package com.example.java_story_bk.models;

public class ReadingInfoOfUser {

    private String create_at;
    private String device_uuid;

    private String chapter_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    private String user_id;

    public void setDevice_uuid(String device_uuid) {
        this.device_uuid = device_uuid;
    }

    public void setChapter_id(String chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getDevice_uuid() {
        return device_uuid;
    }

    public String getChapter_id() {
        return chapter_id;
    }

    public ReadingInfoOfUser(String story_id, String device_uuid, String chapter_id, String user_id) {
        this.device_uuid = device_uuid;
        this.chapter_id = chapter_id;
        this.user_id = user_id;
        this.story_id = story_id;
    }

    public ReadingInfoOfUser() {
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


    private String story_id;
}
