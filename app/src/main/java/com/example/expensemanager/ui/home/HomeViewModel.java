package com.example.expensemanager.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText = new MutableLiveData<>();

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
        expenseCategoryData.add("Food");
        expenseCategoryData.add("Rent");
        expenseCategoryData.add("Entertainment");
        expenseCategoryData.add("Work");
        expenseCategoryData.add("Hobby");
        expenseCategoryData.add("Other");

        mText.setValue("This is the home/dashboard fragment");
    }

    public List<String> getTimeIntervalData() { return timeIntervalData; }

    public List<String> getExpenseCategoryData() { return expenseCategoryData; }

    public LiveData<String> getText() {
        return mText;
    }
}