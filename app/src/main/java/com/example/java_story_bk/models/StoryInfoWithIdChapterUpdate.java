

package com.example.java_story_bk.models;


//@SerializedName("count_starts")

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StoryInfoWithIdChapterUpdate implements Serializable {
    private String _id;
    private String id_new_chapter;

    public void setId_new_chapter(String id_new_chapter) {
        this.id_new_chapter = id_new_chapter;
    }

    public String getId_new_chapter() {
        return id_new_chapter;
    }

    private int count_read;

    public void setCount_read(int count_read) {
        this.count_read = count_read;
    }

    public int getCount_read() {
        return count_read;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setCount_chapters(int count_chapters) {
        this.count_chapters = count_chapters;
    }

    public void setStory_name(String story_name) {
        this.story_name = story_name;
    }

    public void setStory_picture(String story_picture) {
        this.story_picture = story_picture;
    }

    public void setAuhtor_name(String auhtor_name) {
        this.auhtor_name = auhtor_name;
    }

    public void setStory_quick_review(String story_quick_review) {
        this.story_quick_review = story_quick_review;
    }

    public void setStory_genre(String story_genre) {
        this.story_genre = story_genre;
    }

    public void setCompleted_status(int completed_status) {
        this.completed_status = completed_status;
    }

    public void setLinh_thach(int linh_thach) {
        this.linh_thach = linh_thach;
    }

    public void setCount_followers_story(int count_followers_story) {
        this.count_followers_story = count_followers_story;
    }

    public void setCount_stars(float count_stars) {
        this.count_stars = count_stars;
    }

    public String get_id() {
        return _id;
    }

    public String getCreate_at() {
        return create_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public int getCount_chapters() {
        return count_chapters;
    }

    public String getStory_name() {
        return story_name;
    }

    public String getStory_picture() {
        return story_picture;
    }

    public String getAuhtor_name() {
        return auhtor_name;
    }

    public String getStory_quick_review() {
        return story_quick_review;
    }

    public String getStory_genre() {
        return story_genre;
    }

    public int getCompleted_status() {
        return completed_status;
    }

    public int getLinh_thach() {
        return linh_thach;
    }

    public int getCount_followers_story() {
        return count_followers_story;
    }

    public float getCount_stars() {
        return count_stars;
    }

    private StoryInfoWithIdChapterUpdate(String _id, String create_at, String updated_at, int count_chapters, String story_name, String story_picture, String auhtor_name, String story_quick_review, String story_genre, int completed_status, int linh_thach, int count_followers_story, float count_stars, int count_read, String id_new_chapter) {
        this._id = _id;
        this.create_at = create_at;
        this.updated_at = updated_at;
        this.count_chapters = count_chapters;
        this.story_name = story_name;
        this.story_picture = story_picture;
        this.auhtor_name = auhtor_name;
        this.story_quick_review = story_quick_review;
        this.story_genre = story_genre;
        this.completed_status = completed_status;
        this.linh_thach = linh_thach;
        this.count_followers_story = count_followers_story;
        this.count_stars = count_stars;
        this.count_read = count_read;

        this.id_new_chapter = id_new_chapter;
    }

    private String create_at;
    private String updated_at;
    private int count_chapters;
    private String story_name;
    private String story_picture;
    private String auhtor_name;
    private String story_quick_review;
    private String story_genre;
    private int completed_status;
    private int linh_thach;
    private int count_followers_story;
    @SerializedName("count_starts")
    private float count_stars;
}
