package com.example.expensemanager.data.expenses;

import com.example.expensemanager.data.category.CategoryRepository;
import com.example.expensemanager.data.category.model.Category;
import com.example.expensemanager.data.expenses.model.Expense;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


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

    public static boolean removeExpenseById(int id){
        boolean found = false;

        Optional<Expense> optionalExpense = instance.expenses.stream().filter(x -> x.getId() == id).findAny();
        if(optionalExpense.isPresent()){
            instance.expenses.remove(optionalExpense.get());
            found = true;
        }
        return found;
    }

    /**
     * @param title the title that the expense is declared under
     * @param amount the amount that was spent within the expense
     * @param date the date that the expense happened
     * @param category may be null if no category has been assigned
     * @return
     */
    public static Expense addExpense(String title, double amount, LocalDate date, Category category){
        Expense created =  new Expense(getNextId(),title,amount,date, category);
        instance.expenses.add(created);
        CategoryRepository.addExpenseToCategory(created);
        return created;
    }

    public static Expense addExpense(String title, double amount, LocalDate date){
        Category defaultCategory = CategoryRepository.getDefaultCategory();
        return addExpense(title,amount,date,defaultCategory);
    }

    public static Optional<Expense> getExpenseById(int id){
        return instance.expenses.stream().filter(x -> x.getId() == id).findAny();
    }



    private static int getNextId(){
      return getHighestNumber()+1;
    }

    private static int getHighestNumber(){
        return instance.expenses.stream().mapToInt(Expense::getId).max()
                .orElseGet(() -> {return 0; }); //if the array if empty , 1 is returned, otherwise highest number+1

    }


}