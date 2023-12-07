package com.example.expensemanager.ui.reminder;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReminderViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ReminderViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the reminder fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}