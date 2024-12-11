package com.mysoft.proyectopf.model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.util.List;

@ParseClassName("_User")
public class User extends ParseUser {
    public static final String KEY_PHOTO = "photo";
    public static final String KEY_INTERESTS = "interests";

    public User() {
        super();
    }

    public ParseFile getPhoto() {

        return getParseFile(KEY_PHOTO);
    }

    public void setPhoto(ParseFile file) {

        put(KEY_PHOTO, file);
    }

    public List<String> getInterests() {

        return getList(KEY_INTERESTS);
    }

    public void setInterests(List<String> interests) {

        put(KEY_INTERESTS, interests);
    }

    public String getPassword() {

        return getString("password");
    }

    public void setPassword(String password) {

        put("password", password);
    }
}
