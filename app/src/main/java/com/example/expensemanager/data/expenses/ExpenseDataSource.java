package com.example.expensemanager.data.expenses;

import com.example.expensemanager.data.category.CategoryRepository;
import com.example.expensemanager.data.category.model.Category;
import com.example.expensemanager.data.expenses.model.Expense;
import com.example.expensemanager.data.login.Result;
import com.example.expensemanager.data.login.model.LoggedInUser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class ExpenseDataSource {


    public ArrayList<Expense> getData() {
        //provide Default Data here
        ArrayList<Expense> demoData = new ArrayList<>();
        seedData(demoData);
        return demoData;
    }

    private void seedData(ArrayList<Expense> demo){

        //add housing expenses
        int idCounter = 1;

        for (int i = 1; i <= 12; i++) {
            demo.add(new Expense(idCounter++,"Miete",450.00, LocalDate.of(2023,i,1), CategoryRepository.getCategoryById(1).get()));
        }
        demo.add(new Expense(idCounter++, "Miete", 450.00, LocalDate.of(2024,01,01),CategoryRepository.getCategoryById(1).get()));

    }
}