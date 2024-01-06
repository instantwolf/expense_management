package com.example.expensemanager.ui.settings;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import androidx.lifecycle.ViewModelProvider;


import com.example.expensemanager.databinding.FragmentSettingsBinding;
import com.example.expensemanager.ui.login.LoginActivity;
import com.example.expensemanager.ui.login.ChangePassword;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel dashboardViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textSettings;
        //dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        EditText editTextMonthlyAmount = binding.settingsMonthlyAmount;
        EditText editTextYearlyAmount = binding.settingsYearlyAmount;
        EditText editTextNumberDecimal = binding.editTextNumberDecimal;
        EditText editTextNumberDecimal2 = binding.editTextNumberDecimal2;
        EditText editTextNumberDecimal3 = binding.editTextNumberDecimal3;


        Spinner currencySpinner = binding.spinner; // Stellen Sie sicher, dass dies die korrekte ID Ihres Spinners ist
        currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCurrency = parent.getItemAtPosition(position).toString();
                updateEditTextPlaceholders(selectedCurrency);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Optional: Standardverhalten, wenn nichts ausgewählt ist
            }
        });

        TextView textViewLogout = binding.textView14; // Stellen Sie sicher, dass textView14 die richtige ID ist
        textViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        TextView textViewChangePassword = binding.textView13;
        textViewChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    Intent intent = new Intent(getActivity(), ChangePassword.class);
                    startActivity(intent);
                }
            }
        });

        TextWatcher euroTextWatcher = new TextWatcher() {
            private boolean isUpdating = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isUpdating) {
                    isUpdating = true;

                    String str = s.toString();
                    if (!str.endsWith("€") && !str.isEmpty()) {
                        str += "€";
                        s.replace(0, s.length(), str);
                    }

                    isUpdating = false;
                }
            }
        };


        editTextMonthlyAmount.addTextChangedListener(euroTextWatcher);
        editTextYearlyAmount.addTextChangedListener(euroTextWatcher);
        editTextNumberDecimal.addTextChangedListener(euroTextWatcher);
        editTextNumberDecimal2.addTextChangedListener(euroTextWatcher);
        editTextNumberDecimal3.addTextChangedListener(euroTextWatcher);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void updateEditTextPlaceholders(String currency) {
        String currencySymbol;
        switch (currency) {
            case "€ EUR":
                currencySymbol = "€";
                break;
            case "₽ RUB": // Ersetzen Sie dies durch den tatsächlichen Wert für Rubel in Ihrem Spinner
                currencySymbol = "₽";
                break;
            case "$ USD": // Ersetzen Sie dies durch den tatsächlichen Wert für Dollar in Ihrem Spinner
                currencySymbol = "$";
                break;
            default:
                currencySymbol = ""; // Standardfall
        }

        // Aktualisieren Sie den Platzhaltertext der EditText-Elemente
        binding.settingsMonthlyAmount.setHint("0000.00" + currencySymbol);
        binding.settingsYearlyAmount.setHint("0000.00" + currencySymbol);
        binding.editTextNumberDecimal.setHint("000.00" + currencySymbol);
        binding.editTextNumberDecimal2.setHint("000.00" + currencySymbol);
        binding.editTextNumberDecimal3.setHint("000.00" + currencySymbol);

    }

}