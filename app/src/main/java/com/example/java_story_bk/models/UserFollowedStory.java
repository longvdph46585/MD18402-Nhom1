package com.example.java_story_bk.models;

public class UserFollowedStory {
    private String _id, story_id,user_id,id_new_chapter,created_at,updated_at;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setStory_id(String story_id) {
        this.story_id = story_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setId_new_chapter(String id_new_chapter) {
        this.id_new_chapter = id_new_chapter;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getStory_id() {
        return story_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getId_new_chapter() {
        return id_new_chapter;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public UserFollowedStory(String _id, String story_id, String user_id, String id_new_chapter, String created_at, String updated_at) {
        this._id = _id;
        this.story_id = story_id;
        this.user_id = user_id;
        this.id_new_chapter = id_new_chapter;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
