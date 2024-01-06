package com.example.expensemanager.ui.reminder;

public class Reminder {
    private String title;
    private String date;
    private String repeatOption;

    private String amount;

    public Reminder(String title, String date, String repeatOption, String amount) {
        this.title = title;
        this.date = date;
        this.repeatOption = repeatOption;
        this.amount = amount;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDate() {
        return this.date;
    }

    public String getRepeatOption() {
        return this.repeatOption;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public void setDate(String newDate){
        this.date = newDate;
    }

    public void setRepeatOption(String newRepeatOption){
        this.repeatOption = newRepeatOption;
    }

    public String getAmount(){
        return this.amount;
    }

    public void setAmount(String newAmount) {
        this.amount = newAmount;
    }
}

