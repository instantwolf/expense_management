package com.example.expensemanager.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.charts.Pie;


import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText = new MutableLiveData<>();
    private final MutableLiveData<String> selectedCategory = new MutableLiveData<>();

    // Define the time interval data
    public final List<String> timeIntervalData = new ArrayList<>();

    // Define the expense Category data
    public final List<String> expenseCategoryData = new ArrayList<>();

    public HomeViewModel() {
        // Initialize the time interval data
        timeIntervalData.add("1 hour");
        timeIntervalData.add("10 hour");
        timeIntervalData.add("24 hour");
        timeIntervalData.add("48 hour");

        // Initialize the time interval data
        expenseCategoryData.add("All");
        expenseCategoryData.add("Food");
        expenseCategoryData.add("Hobby");
        expenseCategoryData.add("Other");

        mText.setValue("This is the home/dashboard fragment");
    }

    public void setSelectedCategory(String category) {
        selectedCategory.setValue(category);
    }

    public LiveData<String> getSelectedCategory() {
        return selectedCategory;
    }

    public List<String> getTimeIntervalData() { return timeIntervalData; }

    public List<String> getExpenseCategoryData() { return expenseCategoryData; }

    public LiveData<String> getText() {
        return mText;
    }
}