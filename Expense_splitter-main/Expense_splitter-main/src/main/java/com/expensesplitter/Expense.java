package com.expensesplitter;

import java.util.List;

public class Expense {
    private String description;
    private double amount;
    private String paidBy;
    private List<String> splitBetween;

    public Expense(String description, double amount, String paidBy, List<String> splitBetween) {
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
        this.splitBetween = splitBetween;
    }

    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public String getPaidBy() { return paidBy; }
    public List<String> getSplitBetween() { return splitBetween; }
}
