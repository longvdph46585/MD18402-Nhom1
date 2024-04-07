package com.example.java_story_bk.models;

public class ChapterInfo {
    String _id;

    public String get_id() {
        return _id;
    }

    public ChapterInfo(String _id, String created_at, String updated_at, String chapter_name, String
            content_chapter, String story_id, int index
    ) {
        this._id = _id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.chapter_name = chapter_name;
        this.content_chapter = content_chapter;
        this.story_id = story_id;
        this.index = index;

    }

    public String getCreated_at() {
        return created_at;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }

    public void setContent_chapter(String content_chapter) {
        this.content_chapter = content_chapter;
    }

    public void setStory_id(String story_id) {
        this.story_id = story_id;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getChapter_name() {
        return chapter_name;
    }

    public String getContent_chapter() {
        return content_chapter;
    }

    public String getStory_id() {
        return story_id;
    }

    public int getIndex() {
        return index;
    }

    String created_at;
    String updated_at;
    String chapter_name;
    String content_chapter;
    String story_id;
    int index;
}
