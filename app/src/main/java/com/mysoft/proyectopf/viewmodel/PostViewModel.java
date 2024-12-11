package com.mysoft.proyectopf.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mysoft.proyectopf.providers.PostProvider;
import com.parse.ParseFile;

import java.util.List;


public class PostViewModel extends ViewModel {
    private PostProvider postProvider;

    public PostViewModel() {
        postProvider = new PostProvider();
    }

    public LiveData<String> createPost(String title, String description, int duration, String category, double budget, List<ParseFile> images) {
        return postProvider.addPost(title, description, duration, category, budget, images);
    }
}