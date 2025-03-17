package com.expensesplitter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class CalculateSettlementServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Double> balances = ExpenseManager.calculateSettlement();
        
        StringBuilder jsonResponse = new StringBuilder("{");
        for (Map.Entry<String, Double> entry : balances.entrySet()) {
            jsonResponse.append("\"").append(entry.getKey()).append("\":").append(entry.getValue()).append(",");
        }
        
        if (!balances.isEmpty()) jsonResponse.setLength(jsonResponse.length() - 1);
        jsonResponse.append("}");

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
    }
}
