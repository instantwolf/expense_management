package com.example.expensemanager.ui.expense;

import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.expensemanager.R;
//import com.example.expensemanager.data.expenses.model.Expense;
import com.example.expensemanager.databinding.FragmentExpenseBinding;
import com.example.expensemanager.ui.history.ExpenseData;
import com.example.expensemanager.ui.login.LogInForgotPassword;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ExpenseFragment extends Fragment {

    private FragmentExpenseBinding binding;

    private EditText editTextTitle;
    private EditText editTextAmount;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        ExpenseViewModel expenseViewModel =
                new ViewModelProvider(this).get(ExpenseViewModel.class);



        binding = FragmentExpenseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.fragmentExpenseTextViewNewExpense;
        expenseViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        //start
        Button buttonSave = binding.buttonSave;
        editTextTitle = binding.fragmentExpenseEditTextTitle;
        editTextAmount = binding.fragmentExpenseEditTextAmount;

        Button buttonCancel = binding.buttonCancel;
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFields();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewExpense();
            }
        });


        //END
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    //new
    private void createNewExpense() {
        // Annahme: Du hast eine Expense-Klasse mit Konstruktor und getter/setter-Methoden
        String title = editTextTitle.getText().toString();
        String amountString = editTextAmount.getText().toString();
        double amount = Double.parseDouble(amountString);
        String currentDate = getCurrentDate();
        ExpenseData newExpense = new ExpenseData(title,amountString,currentDate);


        // Hier könntest du den neuen Expense speichern oder weitere Aktionen durchführen

        // Erfolgsmeldung anzeigen
        showToast("Expense erfolgreich erstellt");


        // EditText-Felder leeren
        editTextTitle.setText("");
        editTextAmount.setText("");
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        Date currentDate = new Date(System.currentTimeMillis());
        return dateFormat.format(currentDate);
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
    private void clearFields() {
        editTextTitle.setText("");
        editTextAmount.setText("");
        // Hier weitere Felder hinzufügen, die du leeren möchtest
    }
}