package com.example.expensemanager.data.category.model;

import com.example.expensemanager.data.expenses.model.Expense;

import java.util.ArrayList;

public class Category {

    int id;

    String name;

    //ArrayList<Expense> expenses;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
     //   this.expenses = new ArrayList<>();
    }


    public int getId(){ return this.id; }

    public String getName() { return this.name; }

    public int getExpenseCount() {
        return this.expenses.size();
    }
  
    public boolean matches(String name){
        return getEqualityString(this.name).equals(getEqualityString(name));
    }

    private String getEqualityString(String strToTest){
        return strToTest.toLowerCase().trim().replace(" ", "");
    }
}
