package com.mysoft.proyectopf.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mysoft.proyectopf.providers.AuthProvider;


public class MainViewModel extends ViewModel {
    private final AuthProvider authProvider;

    public MainViewModel(Context context) {
        authProvider = new AuthProvider(context);
    }

    public LiveData<String> login(String email, String password) {
        MutableLiveData<String> loginResult = new MutableLiveData<>();
        authProvider.signIn(email, password).observeForever(loginResult::setValue);
        return loginResult;
    }
}
