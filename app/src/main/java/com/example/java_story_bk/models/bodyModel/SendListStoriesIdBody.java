package com.example.java_story_bk.models.bodyModel;

import java.util.ArrayList;

public class SendListStoriesIdBody {
    private ArrayList listIdStories;

    public ArrayList getListIdStories() {
        return listIdStories;
    }

    public void setListIdStories(ArrayList listIdStories) {
        this.listIdStories = listIdStories;
    }

    public SendListStoriesIdBody(ArrayList listIdStories) {
        this.listIdStories = listIdStories;
    }
}
