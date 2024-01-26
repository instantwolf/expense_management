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


    public void seedData(){

        int idCounter = 1;

        for (int i = 1; i <= 12; i++) {
            ExpenseRepository.addExpense("Miete", 450.00,  LocalDate.of(2023,i,1),"housing");
        }
        ExpenseRepository.addExpense("Miete", 450.00,  LocalDate.of(2024,01,01),"housing");
    }
}