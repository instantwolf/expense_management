
package com.example.expensemanager.data.category.model

import com.example.expensemanager.data.expenses.model.Expense


data class Category(val id: Int, val name : String, val expenses : ArrayList<Expense>){

    constructor(id: Int, name: String) : this(id,name, ArrayList());

    fun matches(name:String) : Boolean{
        return equalityString(this.name) == equalityString(name);
    }

    private fun equalityString(strToTest:String):String{
        return strToTest.lowercase().trim().replace(" ", "");
    }
}



