package com.example.expensemanager.data.category;

import com.example.expensemanager.data.category.model.Category;
import com.example.expensemanager.data.expenses.model.Expense;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryRepository {

    private static Category DEFAULT_CATEGORY = new Category(0,"uncategorized");

    private static volatile CategoryRepository instance;

    private CategoryDataSource dataSource;

    private ArrayList<Category> categories;

    // private constructor : singleton access
    private CategoryRepository(CategoryDataSource dataSource) {
        this.dataSource = dataSource;
        this.categories = new ArrayList<>();
        this.categories.add(DEFAULT_CATEGORY);
        this.categories.addAll(dataSource.getData());
    }

    public static CategoryRepository getInstance(CategoryDataSource dataSource) {
        if (instance == null) {
            instance = new CategoryRepository(dataSource);
        }
        return instance;
    }

    public boolean removeCategoryById(int id){
        boolean found = false;

        Optional<Category> optionalCategory = this.categories.stream().filter(x -> x.getId() == id).findAny();
        if(optionalCategory.isPresent()){
            this.categories.remove(optionalCategory.get());
            found = true;
        }
        return found;
    }

    /**
    public static void addDefaultCategory() {
        instance.categories.add(DEFAULT_CATEGORY);
    }
     */

    public static Category addCategoryIfNotExists(String name){

        Category created =  new Category(getNextId(),name);
        instance.categories.add(created);
        return created;
    }

    public static Optional<Category> getCategoryByName(String name){
      Optional<Category> optionalCategory = instance.categories.stream()
              .filter(x -> x.matches(name)).findAny();
      return optionalCategory;
    }

    public static boolean addExpenseToCategory(Expense expense){
        boolean found = false;

        Optional<Category> optionalCategory = getCategoryById(expense.getExpenseCategory().getId());
        if(optionalCategory.isPresent()){
            instance.categories.remove(optionalCategory.get());
            found = true;
        }
        return found;
    }

    public static Category getDefaultCategory(){
        return DEFAULT_CATEGORY;
    }

    public static Optional<Category> getCategoryById(int id){
        return instance.categories.stream().filter(x -> x.getId() == id).findAny();
    }

    private static int getNextId(){
      return getHighestNumber()+1;
    }

    private static int getHighestNumber(){
        return instance.categories.stream().mapToInt(Category::getId).max()
                .orElseGet(() -> {return 0; }); //if the array if empty , 1 is returned, otherwise highest number+1

    }


}