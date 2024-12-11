package com.mysoft.proyectopf.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mysoft.proyectopf.R;
import com.mysoft.proyectopf.adapters.PostAdapter;
import com.mysoft.proyectopf.databinding.FragmentHomeBinding;
import com.mysoft.proyectopf.model.Post;
import com.mysoft.proyectopf.view.HomeActivity;
import com.mysoft.proyectopf.view.PostActivity;
import com.mysoft.proyectopf.viewmodel.HomeViewModel;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    private PostAdapter adapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        setupRecyclerView();
        setupFAB();
        loadPosts();
    }

    private void setupRecyclerView() {
        adapter = new PostAdapter(new ArrayList<>());
        binding.recyclerViewPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewPosts.setAdapter(adapter);
    }

    private void setupFAB() {
        binding.fabAddPost.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PostActivity.class);
            startActivity(intent);
        });
    }

    private void loadPosts() {
        if (getActivity() instanceof HomeActivity) {
            ((HomeActivity) getActivity()).showProgressBar();
        }

        viewModel.getPosts().observe(getViewLifecycleOwner(), result -> {
            if (getActivity() instanceof HomeActivity) {
                ((HomeActivity) getActivity()).hideProgressBar();
            }

            if (result != null) {
                updateUI(result);
            } else {
                showError();
            }
        });
    }

    private void updateUI(List<Post> posts) {
        adapter.updatePosts(posts);
        binding.recyclerViewPosts.setVisibility(View.VISIBLE);
        binding.textViewNoData.setVisibility(posts.isEmpty() ? View.VISIBLE : View.GONE);
    }

    private void showError() {
        Toast.makeText(getContext(), "Error al cargar los posts", Toast.LENGTH_SHORT).show();
        binding.recyclerViewPosts.setVisibility(View.GONE);
        binding.textViewNoData.setVisibility(View.VISIBLE);
        binding.textViewNoData.setText(R.string.error_loading_posts);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
