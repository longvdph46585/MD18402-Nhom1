package com.example.java_story_bk.models.bodyModel;

import com.example.java_story_bk.models.ReadingInfoOfUser;

import java.util.ArrayList;

public class SendReadingInfoOfUser {
    public void setHistory_read(ReadingInfoOfUser history_read) {
        this.history_read = history_read;
    }

    public SendReadingInfoOfUser(ReadingInfoOfUser history_read) {
        this.history_read = history_read;
    }

    private ReadingInfoOfUser history_read;

    public ReadingInfoOfUser getHistory_read() {
        return history_read;
    }
}
