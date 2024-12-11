package com.mysoft.proyectopf.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mysoft.proyectopf.model.Post;
import com.mysoft.proyectopf.providers.PostProvider;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private PostProvider postProvider;

    public HomeViewModel() {
        postProvider = new PostProvider();
    }

    public LiveData<List<Post>> getPosts() {
        return postProvider.getAllPosts();
    }
}