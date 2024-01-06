package com.example.expensemanager.ui.reminder;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.expensemanager.databinding.FragmentReminderBinding;

import java.util.ArrayList;
import java.util.List;

public class ReminderFragment extends Fragment {

    private FragmentReminderBinding binding;
    private ArrayList<Reminder> reminders;

    private LinearLayout cardViewContainer;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ReminderViewModel dashboardViewModel =
                new ViewModelProvider(this).get(ReminderViewModel.class);

        binding = FragmentReminderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textReminder;
        //dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        reminders = new ArrayList<>();
        reminders.add(new Reminder("Miete", "01.02.2024", "Monthly", "789€"));
        reminders.add(new Reminder("Fine", "14.01.2024", "Dont repeat", "123€"));
        reminders.add(new Reminder("Insurance", "15.01.2024", "Yearly", "234€"));

        addReminderCards(reminders);

        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.editTextText.getText().toString();
                String date = binding.editTextDate.getText().toString();
                String repeatOption = binding.spinner2.getSelectedItem().toString();
                String amount = binding.editTextText2.getText().toString();

                Reminder newReminder = new Reminder(title, date, repeatOption, amount);
                reminders.add(newReminder);

                // Log statement to verify the reminder is added
                Log.d("ReminderFragment", "Added new reminder: " + newReminder.getTitle());

                // Call addReminderCards on the main thread
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addReminderCards(reminders);
                    }
                });

                // Clearing the input fields
                binding.editTextText.setText("");
                binding.editTextDate.setText("");
                binding.editTextText2.setText("");
                binding.spinner2.setSelection(0);
            }
        });

       return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void addReminderCards(List<Reminder> reminders) {
        LinearLayout cardViewContainer = binding.cardViewContainerReminder;
        cardViewContainer.removeAllViews();

        for (Reminder reminder : reminders) {
            // Inflate or create a CardView
            CardView cardView = new CardView(getContext());
            cardView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            cardView.setCardElevation(8);
            cardView.setRadius(16);

            // Create a layout to hold the views inside the card
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.setPadding(16, 16, 16, 16);

            // Create TextView for title
            TextView titleView = new TextView(getContext());
            titleView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            titleView.setText(reminder.getTitle());
            titleView.setTextSize(18);
            titleView.setTextColor(Color.BLACK);

            // Create TextView for date
            TextView dateView = new TextView(getContext());
            dateView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            dateView.setText(reminder.getDate());
            dateView.setTextSize(16);

            // Create TextView for repeat option
            TextView repeatOptionView = new TextView(getContext());
            repeatOptionView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            repeatOptionView.setText(reminder.getRepeatOption());
            repeatOptionView.setTextSize(14);

            // Create TextView for amount
            TextView amountView = new TextView(getContext());
            amountView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            amountView.setText(reminder.getAmount());
            amountView.setTextSize(14);
            amountView.setTextColor(Color.BLACK);

            // Add views to the layout
            linearLayout.addView(titleView);
            linearLayout.addView(dateView);
            linearLayout.addView(repeatOptionView);
            linearLayout.addView(amountView);

            // Add the layout to the card
            cardView.addView(linearLayout);

            // Add the card to the container
            cardViewContainer.addView(cardView);

            cardViewContainer.invalidate();
        }
    }
}