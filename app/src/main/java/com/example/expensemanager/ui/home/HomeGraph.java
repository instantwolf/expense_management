package com.example.expensemanager.ui.home;

import android.provider.ContactsContract;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.expensemanager.data.category.CategoryRepository;
import com.example.expensemanager.data.category.model.Category;
import com.example.expensemanager.data.expenses.ExpenseRepository;

import java.util.Collection;
import java.util.List;

public class HomeGraph {

    private AnyChartView anychartView;
    private List<DataEntry> chartData;

    public HomeGraph(AnyChartView view, List<DataEntry> data) {
        anychartView = view;
        chartData = data;
    }

    public void drawGraph() {
        // Fetch and add new data
        ExpenseRepository.getAllExpenses();
        Collection<Category> categories = CategoryRepository.getAllCategories();
        for (Category category: categories) {
            double categoryValue = getCategoryValue(category);
            chartData.add(new ValueDataEntry(category.getName(), categoryValue));
        }

        Pie pie = AnyChart.pie();

        // update and redraw the chart
        pie.data(chartData);
        anychartView.setChart(pie);
        anychartView.invalidate();
    }

    public void clearData() {
        chartData.clear();
    }

    private static double getCategoryValue(Category category) {
        return ExpenseRepository.getAmountByCategoryId(category.getId());
    }
}
