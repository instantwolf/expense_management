package com.example.expensemanager.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.expensemanager.databinding.FragmentHomeBinding;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSpinners();
        observeCategorySelection();
    }

    private void initSpinners() {
        if (getContext() == null) return;

        List<String> timeIntervalData = homeViewModel.getTimeIntervalData();
        List<String> expenseCategoryData = homeViewModel.getExpenseCategoryData();

        Spinner timeIntervalSpinner = binding.timeInterval;
        Spinner expenseCategorySpinner = binding.expenseCategory;

        ArrayAdapter<String> timerIntervalAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, timeIntervalData);
        ArrayAdapter<String> expenseCategoryAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, expenseCategoryData);

        timerIntervalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        expenseCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        timeIntervalSpinner.setAdapter(timerIntervalAdapter);
        expenseCategorySpinner.setAdapter(expenseCategoryAdapter);

        ReadCategorySpinner();
    }

    private void observeCategorySelection(){
        homeViewModel.getSelectedCategory().observe(getViewLifecycleOwner(), this::updateCategoryViews);
    }

    private void ReadCategorySpinner() {
        Spinner categoriesSpinner = binding.expenseCategory;
        categoriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String selectedCategory = parent.getItemAtPosition(position).toString();
                    homeViewModel.setSelectedCategory(selectedCategory);
                } catch (Exception e) {
                    e.printStackTrace(); // Log the exception to find out what's going wrong
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void updateCategoryViews(String category) {
        if (category.equals("All")) {
            binding.cat1.setText("Food");
            binding.cat2.setText("Hobby");
            binding.cat3.setText("Other");
            binding.cat2.setVisibility(View.VISIBLE);
            binding.cat3.setVisibility(View.VISIBLE);
        } else {
            binding.cat1.setText(category);
            binding.cat2.setVisibility(View.GONE);
            binding.cat3.setVisibility(View.GONE);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}