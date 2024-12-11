package com.mysoft.proyectopf.view;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.mysoft.proyectopf.R;
import com.mysoft.proyectopf.databinding.ActivityPostBinding;
import com.mysoft.proyectopf.viewmodel.PostViewModel;
import com.parse.ParseFile;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity {

    private ActivityPostBinding binding;
    private PostViewModel viewModel;
    private List<ParseFile> selectedImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(PostViewModel.class);

        setupSpinner();
        setupListeners();
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categorias_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerCategoria.setAdapter(adapter);
    }

    private void setupListeners() {
        binding.buttonAddImage.setOnClickListener(v -> {
            // TODO: Implement image selection
            Toast.makeText(this, "Image selection not implemented yet", Toast.LENGTH_SHORT).show();
        });

        binding.buttonPublish.setOnClickListener(v -> publishPost());
    }

    private void publishPost() {
        String title = binding.editTextTitle.getText().toString();
        String description = binding.editTextDescription.getText().toString();
        String durationStr = binding.editTextDuration.getText().toString();
        String category = binding.spinnerCategoria.getSelectedItem().toString();
        String budgetStr = binding.editTextBudget.getText().toString();

        if (title.isEmpty() || description.isEmpty() || durationStr.isEmpty() || budgetStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int duration = Integer.parseInt(durationStr);
        double budget = Double.parseDouble(budgetStr);

        viewModel.createPost(title, description, duration, category, budget, selectedImages).observe(this, result -> {
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            if (result.startsWith("Post creado")) {
                finish();
            }
        });
    }
}