package com.example.expensemanager.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.expensemanager.R;
import com.example.expensemanager.data.category.CategoryRepository;
import com.example.expensemanager.data.category.model.Category;
import com.example.expensemanager.data.expenses.ExpenseRepository;
import com.example.expensemanager.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Collection;
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

        AnyChartView anyChartView = view.findViewById(R.id.anychartview);
        Pie pie = AnyChart.pie();

        List<DataEntry> chartData = new ArrayList<>();
        ExpenseRepository.getAllExpenses();
        Collection<Category> categories = CategoryRepository.getAllCategories();
        for (Category category: categories) {
            double categoryValue = getCategoryValue(category);
            System.out.println("Testing Values: " + category.getName() );
            chartData.add(new ValueDataEntry(category.getName(), categoryValue));
        }

        pie.data(chartData);
        anyChartView.setChart(pie);
    }

    private double getCategoryValue(Category category) {
        return ExpenseRepository.getAmountByCategoryId(category.getId());
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
        switch (category) {
            case "All":
                // Show all categories
                binding.groupCategory1.setVisibility(View.VISIBLE);
                binding.groupCategory2.setVisibility(View.VISIBLE);
                binding.groupCategory3.setVisibility(View.VISIBLE);

                // Set texts for views
                binding.cat1.setText("Food");
                binding.money1.setText("261.52€");
                binding.percent1.setText("61.15%");

                binding.cat2.setText("Hobby");
                binding.money2.setText("123.45€");
                binding.percent2.setText("28.87%");

                binding.cat3.setText("Other");
                binding.money3.setText("42.96€");
                binding.percent3.setText("9.98%");
                break;

            case "Food":
                // Show only Food category
                binding.groupCategory1.setVisibility(View.VISIBLE);
                binding.groupCategory2.setVisibility(View.GONE);
                binding.groupCategory3.setVisibility(View.GONE);
                binding.cat1.setText("Food");
                binding.money1.setText("261,52€");
                binding.percent1.setText("61.15%");
                break;

            case "Hobby":
                // Show only Home category
                binding.groupCategory1.setVisibility(View.GONE);
                binding.groupCategory2.setVisibility(View.VISIBLE);
                binding.groupCategory3.setVisibility(View.GONE);
                binding.cat2.setText("Hobby");
                binding.money2.setText("123,45€");
                binding.percent2.setText("28.87%");
                break;

            case "Other":
                // Show only Other category
                binding.groupCategory1.setVisibility(View.GONE);
                binding.groupCategory2.setVisibility(View.GONE);
                binding.groupCategory3.setVisibility(View.VISIBLE);
                binding.cat3.setText("Other");
                binding.money3.setText("42,69€");
                binding.percent3.setText("9.98%");
                break;

            default:
                // Hide all categories
                binding.groupCategory1.setVisibility(View.GONE);
                binding.groupCategory2.setVisibility(View.GONE);
                binding.groupCategory3.setVisibility(View.GONE);
                break;
        }

        //        if (category.equals("All")) {
//            binding.cat1.setText("Food");
//            binding.cat2.setText("Hobby");
//            binding.cat3.setText("Other");
//            binding.cat2.setVisibility(View.VISIBLE);
//            binding.cat3.setVisibility(View.VISIBLE);
//        } else {
//            binding.cat1.setText(category);
//            binding.cat2.setVisibility(View.GONE);
//            binding.cat3.setVisibility(View.GONE);
//        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}