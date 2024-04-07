package com.example.java_story_bk.models;

public class Chapter {
    String chapter_name;
    String _id;

    public String getChapter_name() {
        return chapter_name;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String get_id() {
        return _id;
    }

    public int getIndex() {
        return index;
    }

    int index;

    public Chapter(String id, int index, String chapter_name) {
        this.chapter_name = chapter_name;
        this.index = index;
        this._id = id;
    }
}
