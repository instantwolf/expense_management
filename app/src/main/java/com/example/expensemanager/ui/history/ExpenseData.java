package com.example.expensemanager.ui.history;

public class ExpenseData {
    private String title;
    private String date;
    private String amount;

    public ExpenseData(String title, String date, String amount) {
        this.title = title;
        this.date = date;
        this.amount = amount;
    }

    // Getter und Setter Methoden
    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }
}
