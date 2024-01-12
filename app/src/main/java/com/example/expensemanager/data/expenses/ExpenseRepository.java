package com.example.expensemanager.data.expenses;

import com.example.expensemanager.data.expenses.model.Expense;
import com.example.expensemanager.data.login.Result;
import com.example.expensemanager.data.login.model.LoggedInUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * Class that requests authentication and expenses information from the remote data source and
 * maintains an in-memory cache of login status and expenses credentials information.
 */
public class ExpenseRepository {

    private static volatile ExpenseRepository instance;

    private ExpenseDataSource dataSource;

    // If expenses credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private ArrayList<Expense> expenses;

    // private constructor : singleton access
    private ExpenseRepository(ExpenseDataSource dataSource) {
        this.dataSource = dataSource;
        this.expenses = new ArrayList<>();
    }

    public static ExpenseRepository getInstance(ExpenseDataSource dataSource) {
        if (instance == null) {
            instance = new ExpenseRepository(dataSource);
        }
        return instance;
    }

    public boolean removeExpenseById(int id){
        boolean found = false;

        Optional<Expense> optionalExpense = this.expenses.stream().filter(x -> x.getId() == id).findAny();
        if(optionalExpense.isPresent()){
            this.expenses.remove(optionalExpense.get());
            found = true;
        }
        return found;
    }

    public void addTestExpense() {
        addExpense("test",0.00, LocalDate.now());
    }

    public Expense addExpense(String title, double amount, LocalDate date){
        Expense created =  new Expense(getNextId(),title,amount,date);
        this.expenses.add(created);
        return created;
    }

    public Optional<Expense> getExpenseById(int id){
        return this.expenses.stream().filter(x -> x.getId() == id).findAny();
    }





    private int getNextId(){
      return getHighestNumber()+1;
    }

    private int getHighestNumber(){
        return this.expenses.stream().mapToInt(Expense::getId).max()
                .orElseGet(() -> {return 0; }); //if the array if empty , 1 is returned, otherwise highest number+1

    }


}