package com.mysoft.proyectopf.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainViewModelFactory implements ViewModelProvider.Factory {
    private Context context;

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        // Verificar si la clase de ViewModel es assignable a MainViewModel
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            // Crear una instancia de MainViewModel con el contexto proporcionado
            return (T) (MainViewModel) new MainViewModel(context);
        }
        // Si la clase de ViewModel no es assignable a MainViewModel, lanzar una excepción
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
