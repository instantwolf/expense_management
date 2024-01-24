package com.example.expensemanager.data.expenses.model;

import com.example.expensemanager.data.category.model.Category;

import java.time.LocalDate;

public class Expense {

    int id;

    String title;

    double amount;

    LocalDate date;

    Category category;

    public Expense(int id, String title, double amount, LocalDate date, Category category) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }


    public int getId(){ return this.id; }

    public Category getCategory(){ return  this.category; }

}
