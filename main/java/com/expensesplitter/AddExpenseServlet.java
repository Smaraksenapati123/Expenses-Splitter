package com.expensesplitter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class AddExpenseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String description = request.getParameter("description");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String paidBy = request.getParameter("paidBy");
        String[] splitBetween = request.getParameterValues("splitBetween");

        Expense expense = new Expense(description, amount, paidBy, Arrays.asList(splitBetween));
        ExpenseManager.addExpense(expense);

        response.setContentType("application/json");
        response.getWriter().write("{\"status\":\"success\"}");
    }
}
