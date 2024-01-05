package com.example.expensemanager.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.expensemanager.databinding.FragmentSettingsBinding;

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