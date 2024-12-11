package com.mysoft.proyectopf.providers;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mysoft.proyectopf.model.Post;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class PostProvider {

    public LiveData<String> addPost(String title, String description, int duration, String category, double budget, List<ParseFile> images) {
        MutableLiveData<String> result = new MutableLiveData<>();
        Post post = new Post();
        post.setTitle(title);
        post.setDescription(description);
        post.setDuration(duration);
        post.setCategory(category);
        post.setBudget(budget);
        post.setImages(images);
        post.setUser(ParseUser.getCurrentUser());

        post.saveInBackground(e -> {
            if (e == null) {
                result.setValue("Post creado exitosamente");
            } else {
                result.setValue("Error al crear el post: " + e.getMessage());
            }
        });
        return result;
    }

    public LiveData<List<Post>> getPostsByCurrentUser() {
        MutableLiveData<List<Post>> postsLiveData = new MutableLiveData<>();
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.orderByDescending("createdAt");

        query.findInBackground((posts, e) -> {
            if (e == null) {
                postsLiveData.setValue(posts);
            } else {
                postsLiveData.setValue(null);
            }
        });
        return postsLiveData;
    }

    public LiveData<List<Post>> getAllPosts() {
        MutableLiveData<List<Post>> postsLiveData = new MutableLiveData<>();
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.orderByDescending("createdAt");

        query.findInBackground((posts, e) -> {
            if (e == null) {
                postsLiveData.setValue(posts);
            } else {
                postsLiveData.setValue(null);
            }
        });
        return postsLiveData;
    }

    public LiveData<List<Post>> getFilteredPosts(String category, double minBudget, double maxBudget) {
        MutableLiveData<List<Post>> postsLiveData = new MutableLiveData<>();
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        if (category != null && !category.isEmpty()) {
            query.whereEqualTo(Post.KEY_CATEGORY, category);
        }
        query.whereGreaterThanOrEqualTo(Post.KEY_BUDGET, minBudget);
        query.whereLessThanOrEqualTo(Post.KEY_BUDGET, maxBudget);
        query.orderByDescending("createdAt");

        query.findInBackground((posts, e) -> {
            if (e == null) {
                postsLiveData.setValue(posts);
            } else {
                postsLiveData.setValue(null);
            }
        });
        return postsLiveData;
    }
}