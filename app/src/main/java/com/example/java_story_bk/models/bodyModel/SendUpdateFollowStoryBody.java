package com.example.java_story_bk.models.bodyModel;

public class SendUpdateFollowStoryBody {
    private  String user_id,story_id;
    private  boolean status_follow;
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setStory_id(String story_id) {
        this.story_id = story_id;
    }



    public String getUser_id() {
        return user_id;
    }

    public void setStatus_follow(boolean status_follow) {
        this.status_follow = status_follow;
    }

    public boolean isStatus_follow() {
        return status_follow;
    }

    public String getStory_id() {
        return story_id;
    }


    public SendUpdateFollowStoryBody(String userId, String storyId, boolean statusFollow) {
        user_id = userId;
        story_id = storyId;
        status_follow = statusFollow;
    }
}
