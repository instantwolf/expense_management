
package com.example.expensemanager.data.expenses.model

import com.example.expensemanager.data.category.model.Category
import java.time.LocalDate

data class Expense(val id: Int, val title : String, val amount : Double, val date : LocalDate,val expenseCategory: Category)
