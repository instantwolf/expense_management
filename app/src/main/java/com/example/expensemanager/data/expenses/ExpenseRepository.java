package com.example.expensemanager.data.expenses;

import com.example.expensemanager.data.category.CategoryRepository;
import com.example.expensemanager.data.category.model.Category;
import com.example.expensemanager.data.expenses.model.Expense;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
       // CategoryRepository.addExpenseToCategory(created);
        return created;
    }

    public static Expense addExpense(String title, double amount, LocalDate date){
        Category defaultCategory = CategoryRepository.getDefaultCategory();
        return addExpense(title,amount,date,defaultCategory);
    }

    public static Optional<Expense> getExpenseById(int id){
        return instance.expenses.stream().filter(x -> x.getId() == id).findAny();
    }

    public static Collection<Expense> getAllExpenses(){
        return instance.expenses;
    }

    public static Collection<Expense> getAllExpensesSortedByDateAsc(){
        return getSortedExpenses(Expense::compareByDateAsc);
    }

    public static Collection<Expense> getAllExpensesSortedByDateDesc(){
        return getSortedExpenses(Expense::compareByDateDesc);
    }

    public static Collection<Expense> getAllExpensesSortedByAmountAsc(){
        return getSortedExpenses(Expense::compareByDateAsc);
    }

    public static Collection<Expense> getAllExpensesSortedByAmountDesc(){
        return getSortedExpenses(Expense::compareByAmountDesc);
    }


    public static Collection<Expense> getExpensesByCategoryId(int id){
        return instance.expenses.stream()
                .filter(x -> x.getCategory().getId() == id)
                .collect(Collectors.toList());
    }

    public static Collection<Expense> getExpensesByDateRange(LocalDate from, LocalDate to){
        return instance.expenses.stream()
                .filter(x ->
                        x.getDate().compareTo(from) >= 0
                                && x.getDate().compareTo(to) <= 0)
                .collect(Collectors.toList());
    }

    public static Collection<Expense> getSortedExpenses(Comparator<Expense> comp){
        List<Expense> listToSort = instance.expenses;
        Collections.sort(listToSort, comp);
        return listToSort;
    }

    //if the array if empty , 1 is returned, otherwise highest number+1
    private static int getNextId(){
      return getHighestNumber()+1;
    }

    private static int getHighestNumber(){
        return instance.expenses.stream().mapToInt(Expense::getId).max()
                .orElse(0);
    }


}