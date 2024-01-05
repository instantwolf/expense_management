package com.example.expensemanager.ui.history;

import androidx.cardview.widget.CardView;
import android.widget.RelativeLayout; // Import für RelativeLayout
import android.widget.ImageView; // Import für ImageView
import android.widget.TextView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import android.widget.LinearLayout;

import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import com.example.expensemanager.R;
import android.widget.PopupMenu;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.graphics.Color;
import android.util.TypedValue;
import androidx.constraintlayout.widget.ConstraintLayout; // Import für ConstraintLayout

import com.example.expensemanager.databinding.FragmentHistoryBinding;

public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding; // Deklarieren Sie binding als Klassenvariable


    private List<ExpenseData> expenses; // Deklarieren Sie expenses als Klassenvariable
    private ConstraintLayout container; // Deklarieren Sie container als Klassenvariable

    private LinearLayout cardViewContainer;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        this.container = (ConstraintLayout) binding.getRoot();
        cardViewContainer = root.findViewById(R.id.cardViewContainer);
        this.expenses = new ArrayList<>(); // Korrekter Variablenname

        expenses.add(new ExpenseData("Einkauf", "01.04.2024", "€30.00"));
        expenses.add(new ExpenseData("Restaurant", "02.04.2024", "€50.00"));
        expenses.add(new ExpenseData("Tanken", "03.04.2024", "€70.00"));
        // Weitere Daten hinzufügen


        addExpenseCards(expenses);

        setUpSearchView();
        setUpSpinner();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void addExpenseCards(List<ExpenseData> expenses) {

        cardViewContainer.removeAllViews(); // Leeren des Containers vor dem Hinzufügen neuer Ansichten

        int previousCardViewId = R.id.SearchView; // Starten Sie mit der ID des SearchView

        for (ExpenseData expense : expenses) {
            CardView card = new CardView(getActivity());
            int cardId = View.generateViewId();
            card.setId(cardId);

            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT);

            layoutParams.topToBottom = previousCardViewId; // Positionieren unterhalb der vorherigen CardView
            layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.topMargin = dpToPx(10);

            card.setLayoutParams(layoutParams);
            card.setCardElevation(dpToPx(10));
            card.setRadius(dpToPx(10));

            // Erstellen des RelativeLayouts
            RelativeLayout relativeLayout = new RelativeLayout(getActivity());
            relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT));
            relativeLayout.setPadding(dpToPx(10), dpToPx(10), dpToPx(10), dpToPx(10));

            // Hinzufügen der Views zum RelativeLayout
            TextView titleView = new TextView(getActivity());
            titleView.setId(View.generateViewId());
            titleView.setText(expense.getTitle());
            titleView.setTextColor(Color.BLACK);

            TextView dateTimeView = new TextView(getActivity());
            dateTimeView.setId(View.generateViewId());
            dateTimeView.setText(expense.getDate());
            dateTimeView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

            ImageView colouredDot = new ImageView(getActivity());
            colouredDot.setId(View.generateViewId());
            // Stellen Sie sicher, dass das Drawable @drawable/coloured_dot_1 existiert
            colouredDot.setImageResource(R.drawable.coloured_dot_1);

            TextView amountView = new TextView(getActivity());
            amountView.setText(expense.getAmount());
            amountView.setTextColor(Color.BLACK);

            // Positionieren Sie die Views im RelativeLayout
            RelativeLayout.LayoutParams titleLayoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            titleLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            titleLayoutParams.addRule(RelativeLayout.END_OF, colouredDot.getId());
            titleView.setLayoutParams(titleLayoutParams);

            RelativeLayout.LayoutParams dateTimeLayoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            dateTimeLayoutParams.addRule(RelativeLayout.BELOW, titleView.getId());
            dateTimeLayoutParams.addRule(RelativeLayout.END_OF, colouredDot.getId());
            dateTimeView.setLayoutParams(dateTimeLayoutParams);

            RelativeLayout.LayoutParams dotLayoutParams = new RelativeLayout.LayoutParams(
                    dpToPx(20), dpToPx(20));
            dotLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
            dotLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            colouredDot.setLayoutParams(dotLayoutParams);

            RelativeLayout.LayoutParams amountLayoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            amountLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            amountLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
            amountView.setLayoutParams(amountLayoutParams);

            // Hinzufügen der Views zum RelativeLayout
            relativeLayout.addView(titleView);
            relativeLayout.addView(dateTimeView);
            relativeLayout.addView(colouredDot);
            relativeLayout.addView(amountView);

            // Hinzufügen des RelativeLayouts zur CardView
            card.addView(relativeLayout);

            // Hinzufügen der CardView zum Container

            cardViewContainer.addView(card);
            previousCardViewId = cardId;


        }
    }


    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }

    public void sortExpensesByAmount(List<ExpenseData> expenses) {
        // Verwenden Sie einen Comparator, um die Ausgaben nach dem Betrag zu sortieren
        Collections.sort(expenses, new Comparator<ExpenseData>() {
            @Override
            public int compare(ExpenseData expense1, ExpenseData expense2) {
                // Nehmen Sie die Beträge der beiden Ausgaben
                String amount1 = expense1.getAmount().replace("€", ""); // Entfernen Sie das Währungssymbol
                String amount2 = expense2.getAmount().replace("€", "");

                // Konvertieren Sie die Beträge in Dezimalzahlen
                double doubleAmount1 = Double.parseDouble(amount1);
                double doubleAmount2 = Double.parseDouble(amount2);

                // Vergleichen Sie die Beträge und geben Sie das Ergebnis zurück
                // Beachten Sie, dass hier von größtem zu kleinstem Betrag sortiert wird
                if (doubleAmount1 > doubleAmount2) {
                    return -1;
                } else if (doubleAmount1 < doubleAmount2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }

    public void setUpSpinner() {
        Spinner spinner = binding.historySpinner;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                if ("Amount".equals(selected)) {
                    sortExpensesByAmount(expenses);
                    cardViewContainer.removeAllViews(); // Entfernt alle vorhandenen Views
                    addExpenseCards(expenses);  // Fügt die sortierten CardViews hinzu
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nichts zu tun hier
            }
        });
    }

    private void setUpSearchView() {
        SearchView searchView = binding.SearchView; // Ersetzen Sie mit Ihrer korrekten Binding-Referenz
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterExpenses(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterExpenses(newText);
                return true;
            }
        });
    }

    private void filterExpenses(String query) {
        List<ExpenseData> filteredList = new ArrayList<>();
        for (ExpenseData expense : expenses) {
            if (expense.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(expense);
            }
        }
        addExpenseCards(filteredList);
    }






}
