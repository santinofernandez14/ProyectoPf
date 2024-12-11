package com.mysoft.proyectopf.view;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.mysoft.proyectopf.R;
import com.mysoft.proyectopf.databinding.ActivityMainBinding;
import com.mysoft.proyectopf.providers.AuthProvider;
import com.mysoft.proyectopf.util.Validaciones;
import com.mysoft.proyectopf.viewmodel.MainViewModel;
import com.mysoft.proyectopf.viewmodel.MainViewModelFactory;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;
    private AuthProvider authProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this, new MainViewModelFactory()).get(MainViewModel.class);
        authProvider = new AuthProvider(this);
        manejarEventos();
    }

    private void manejarEventos() {
        binding.tvRegistro.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        binding.btLogin.setOnClickListener(v -> {
            String email = binding.itUsuario.getText().toString().trim();
            String pass = binding.itPassword.getText().toString().trim();
            if (!Validaciones.validarMail(email)) {
                showToast("Email incorrecto");
                return;
            }
            if (!Validaciones.controlarPasword(pass)) {
                showToast("Password incorrecto");
                return;
            }
            viewModel.login(email, pass).observe(MainActivity.this, user_id -> {
                if (user_id != null) {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    showToast("Login fallido");
                }
            });
        });
    }

    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        limpiarCampos();
    }

    private void limpiarCampos() {
        binding.itUsuario.setText("");
        binding.itPassword.setText("");
    }
}
