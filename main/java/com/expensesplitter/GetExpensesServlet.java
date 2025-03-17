package com.expensesplitter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class GetExpensesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Expense> expenses = ExpenseManager.getExpenses();

        StringBuilder jsonResponse = new StringBuilder("[");
        for (Expense expense : expenses) {
            jsonResponse.append("{")
                    .append("\"description\":\"").append(expense.getDescription()).append("\",")
                    .append("\"amount\":").append(expense.getAmount()).append(",")
                    .append("\"paidBy\":\"").append(expense.getPaidBy()).append("\",")
                    .append("\"splitBetween\":").append(expense.getSplitBetween().toString())
                    .append("},");
        }
        
        if (expenses.size() > 0) jsonResponse.setLength(jsonResponse.length() - 1);
        jsonResponse.append("]");

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
    }
}
