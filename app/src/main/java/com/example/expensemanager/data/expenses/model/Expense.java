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
    public static int compareByDateAsc(Expense e1, Expense e2){
       return e1.getDate().compareTo(e2.getDate());
    }

    public static int compareByDateDesc(Expense e1, Expense e2){
        return e1.getDate().compareTo(e2.getDate()) * -1 ;
    }

    public static int compareByAmountDesc(Expense e1, Expense e2){
        return Double.compare(e1.getAmount(), e2.getAmount()) * -1;
    }

    public static int compareByAmountAsc(Expense e1, Expense e2){
        return Double.compare(e1.getAmount(), e2.getAmount()) ;
    }

    public double getAmount(){ return this.amount; }

    public LocalDate getDate(){ return this.date; }

    public int getId(){ return this.id; }

    public Category getCategory(){ return  this.category; }

}
