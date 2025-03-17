package com.expensesplitter;

import java.util.*;

public class ExpenseManager {
    private static final List<Expense> expenses = new ArrayList<>();
    private static final List<Member> members = new ArrayList<>();

    public static void addMember(String name) {
        members.add(new Member(name));
    }

    public static void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public static List<Expense> getExpenses() {
        return expenses;
    }

    public static Map<String, Double> calculateSettlement() {
        Map<String, Double> balances = new HashMap<>();

        for (Member member : members) {
            balances.put(member.getName(), 0.0);
        }

        for (Expense expense : expenses) {
            String paidBy = expense.getPaidBy();
            double amount = expense.getAmount();
            List<String> splitBetween = expense.getSplitBetween();
            double splitAmount = amount / splitBetween.size();

            balances.put(paidBy, balances.get(paidBy) + amount);

            for (String member : splitBetween) {
                balances.put(member, balances.get(member) - splitAmount);
            }
        }
        
        return balances;
    }
}
