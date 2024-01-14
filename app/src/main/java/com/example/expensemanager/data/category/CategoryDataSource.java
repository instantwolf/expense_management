package com.example.expensemanager.data.category;

import com.example.expensemanager.data.category.model.Category;
import com.example.expensemanager.data.login.Result;
import com.example.expensemanager.data.login.model.LoggedInUser;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class CategoryDataSource {

    public ArrayList<Category> getData() {
            //provide Default Data here
            ArrayList<Category> demoData = new ArrayList<>();
            seedData(demoData);
            return demoData;
    }

    private void seedData(ArrayList<Category> demo){
        demo.add(new Category(1, "housing"));
        demo.add(new Category(2, "groceries"));
        demo.add(new Category(3, "education"));
        demo.add(new Category(4, "transportation"));
        demo.add(new Category(5, "entertainment"));
        demo.add(new Category(6, "amazon"));
    }
}