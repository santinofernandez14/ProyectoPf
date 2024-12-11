package com.mysoft.proyectopf.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mysoft.proyectopf.R;
import com.mysoft.proyectopf.databinding.FragmentFiltrosBinding;

public class FiltrosFragment extends Fragment {
    private FragmentFiltrosBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFiltrosBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Initialize your views and set up your logic here
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}