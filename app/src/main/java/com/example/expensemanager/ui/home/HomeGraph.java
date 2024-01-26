package com.example.expensemanager.ui.home;

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
    private static List<DataEntry> chartData;

    private static Pie pie;

    public HomeGraph(AnyChartView view, List<DataEntry> data, Pie pie) {
        this.anychartView = view;
        this.chartData = data;
        this.pie = pie;
    }

    public void drawGraph() {
        // Fetch and add new data
        ExpenseRepository.getAllExpenses();
        Collection<Category> categories = CategoryRepository.getAllCategories();
        for (Category category: categories) {
            double categoryValue = getCategoryValue(category);
            System.out.println("Testing: " + categoryValue);
            chartData.add(new ValueDataEntry(category.getName(), categoryValue));
        }

                // update and redraw the chart
        pie.data(chartData);
        anychartView.setChart(pie);
        anychartView.invalidate();
    }

    public static void setData() {
        pie.data(chartData);
    }

    private static double getCategoryValue(Category category) {
        return ExpenseRepository.getAmountByCategoryId(category.getId());
    }
}
