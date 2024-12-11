package com.mysoft.proyectopf.providers;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mysoft.proyectopf.model.User;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.util.List;

public class AuthProvider {

    //TODO revisar este constructor
    public AuthProvider(Context context) {
    }

    public LiveData<String> signIn(String username, String password) {
        MutableLiveData<String> authResult = new MutableLiveData<>();
        ParseUser.logInInBackground(username, password, (user, e) -> {
            if (e == null) {
                authResult.setValue(user.getObjectId());
                Log.d("AuthProvider", "Usuario autenticado exitosamente: " + user.getObjectId());
            } else {
                Log.e("AuthProvider", "Error en inicio de sesión: ", e);
                authResult.setValue(null);
            }
        });
        return authResult;
    }

    public LiveData<String> signUp(String username, String email, String password) {
        MutableLiveData<String> authResult = new MutableLiveData<>();
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.signUpInBackground(e -> {
            if (e == null) {
                authResult.setValue(user.getObjectId());
                Log.d("AuthProvider", "Usuario registrado exitosamente: " + user.getObjectId());
            } else {
                Log.e("AuthProvider", "Error en registro: ", e);
                authResult.setValue(null);
            }
        });
        return authResult;
    }


    public LiveData<Boolean> updateUserProfile(List<String> interests, ParseFile photo) {
        MutableLiveData<Boolean> updateResult = new MutableLiveData<>();
        User currentUser = (User) ParseUser.getCurrentUser();
        if (currentUser != null) {
            currentUser.setInterests(interests);
            if (photo != null) {
                currentUser.setPhoto(photo);
            }
            currentUser.saveInBackground(e -> {
                if (e == null) {
                    updateResult.setValue(true);
                } else {
                    updateResult.setValue(false);
                }
            });
        } else {
            updateResult.setValue(false);
        }
        return updateResult;
    }

    public LiveData<User> getCurrentUser() {
        MutableLiveData<User> currentUser = new MutableLiveData<>();
        User user = (User) ParseUser.getCurrentUser();
        if (user != null) {
            currentUser.setValue(user);
        }
        return currentUser;
    }

    public LiveData<Boolean> logout() {
        MutableLiveData<Boolean> logoutResult = new MutableLiveData<>();
        ParseUser.logOutInBackground(e -> {
            if (e == null) {
                logoutResult.setValue(true);
                Log.d("AuthProvider", "Usuario desconectado.");
            } else {
                logoutResult.setValue(false);
                Log.e("AuthProvider", "Error al desconectar al usuario: ", e);
            }
        });
        return logoutResult;
    }
}
