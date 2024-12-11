package com.mysoft.proyectopf.model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.List;

@ParseClassName("Post")
public class Post extends ParseObject {
    public static final String KEY_TITLE = "title";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_DURATION = "duration";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_BUDGET = "budget";
    public static final String KEY_IMAGES = "images";
    public static final String KEY_USER = "user";

    public String getTitle() {
        return getString(KEY_TITLE);
    }

    public void setTitle(String title) {
        put(KEY_TITLE, title);
    }

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public int getDuration() {
        return getInt(KEY_DURATION);
    }

    public void setDuration(int duration) {
        put(KEY_DURATION, duration);
    }

    public String getCategory() {
        return getString(KEY_CATEGORY);
    }

    public void setCategory(String category) {
        put(KEY_CATEGORY, category);
    }

    public double getBudget() {
        return getDouble(KEY_BUDGET);
    }

    public void setBudget(double budget) {
        put(KEY_BUDGET, budget);
    }

    public List<ParseFile> getImages() {
        return getList(KEY_IMAGES);
    }

    public void setImages(List<ParseFile> images) {
        put(KEY_IMAGES, images);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }
}
