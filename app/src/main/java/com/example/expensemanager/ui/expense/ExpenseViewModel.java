package com.example.expensemanager.ui.expense;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExpenseViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ExpenseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the expenses fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}