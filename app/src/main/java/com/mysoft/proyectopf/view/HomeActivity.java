package com.mysoft.proyectopf.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mysoft.proyectopf.R;
import com.mysoft.proyectopf.databinding.ActivityHomeBinding;
import com.mysoft.proyectopf.view.fragments.ChatsFragment;
import com.mysoft.proyectopf.view.fragments.FiltrosFragment;
import com.mysoft.proyectopf.view.fragments.HomeFragment;
import com.mysoft.proyectopf.view.fragments.PerfilFragment;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LayoutInflater inflater = LayoutInflater.from(this);
        View progressBarLayout = inflater.inflate(R.layout.progress_layout, binding.mainCont, false);
        binding.mainCont.addView(progressBarLayout);

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.itemHome) {
                openFragment(HomeFragment.newInstance());
            } else if (item.getItemId() == R.id.itemChats) {
                openFragment(new ChatsFragment());
            } else if (item.getItemId() == R.id.itemPerfil) {
                openFragment(new PerfilFragment());
            } else if (item.getItemId() == R.id.itemFiltros) {
                openFragment(new FiltrosFragment());
            }
            return true;
        });

        openFragment(HomeFragment.newInstance());
    }

    private void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    public void hideProgressBar() {
        View progressBarLayout = findViewById(R.id.progress_layout);
        if (progressBarLayout != null) {
            progressBarLayout.setVisibility(View.GONE);
        }
    }

    public void showProgressBar() {
        View progressBarLayout = findViewById(R.id.progress_layout);
        if (progressBarLayout != null) {
            progressBarLayout.setVisibility(View.VISIBLE);
        }
    }
}
