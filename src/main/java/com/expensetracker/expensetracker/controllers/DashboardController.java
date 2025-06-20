package com.expensetracker.expensetracker.controllers;
import com.expensetracker.expensetracker.models.Transaction;
import com.expensetracker.expensetracker.services.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import java.util.Map;

@Controller
public class DashboardController {
    @Autowired
    private TransactionService transactionService;
    // This method maps the root URL ("/") to the dashboard view
    @GetMapping("/")
    public String showDashboard(Model model,HttpServletRequest request) {
        // Returns the name of the HTML template (index.html) inside /templates
        model.addAttribute("pageTitle", "Dashboard - Expense Tracker");
        model.addAttribute("requestURI", request.getRequestURI()); // Add request URI to the model
        return "index";
    }

    @GetMapping("/reports")
    public String showReports(Model model, HttpServletRequest request) {
        // Returns the name of the HTML template (index.html) inside /templates
        model.addAttribute("requestURI", request.getRequestURI()); // Add request URI to the model
        model.addAttribute("pageTitle", "Reports - Expense Tracker");
        return "reports";
    }

    // Method to retrieve total expenses for monthly, weekly, and yearly via AJAX
    @GetMapping("/total-expenses")
    @ResponseBody
    public ResponseEntity<Map<String, Double>> getTotalExpenses() {
        Map<String, Double> expenses = transactionService.getTotalExpenses();
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/total-incomes")
    @ResponseBody
    public ResponseEntity<Map<String, Double>> getTotalIncomes() {
        Map<String, Double> incomes = transactionService.getTotalIncomes();
        return ResponseEntity.ok(incomes);
    }


    @GetMapping("/total-transactions")
    @ResponseBody
    public ResponseEntity<Map<String, Double>> getTotalTransactions() {
        Map<String, Double> incomes = transactionService.getTotalTransactions();
        return ResponseEntity.ok(incomes);
    }


    // Endpoint for total incomes by month
    @GetMapping("/total-incomes-by-month")
    @ResponseBody
    public ResponseEntity<Map<String, Double>> getTotalIncomesByMonth() {
        Map<String, Double> incomesByMonth = transactionService.getTotalIncomesByMonth();
        return ResponseEntity.ok(incomesByMonth);
    }

    // Endpoint for total expenses by month
    @GetMapping("/total-expenses-by-month")
    @ResponseBody
    public ResponseEntity<Map<String, Double>> getTotalExpensesByMonth() {
        Map<String, Double> expensesByMonth = transactionService.getTotalExpensesByMonth();
        return ResponseEntity.ok(expensesByMonth);
    }
    //
    // Endpoint for total transactions count by month
    @GetMapping("/total-transactions-by-month")
    @ResponseBody
    public ResponseEntity<Map<String, Integer>> getTotalTransactionsByMonth() {
        Map<String, Integer> transactionsByMonth = transactionService.getTotalTransactionsByMonth();
        return ResponseEntity.ok(transactionsByMonth);
    }
    
    // Endpoint to get recent transactions
    @GetMapping("/api/transactions/recent")
    @ResponseBody
    public ResponseEntity<List<Transaction>> getRecentTransactions() {
        List<Transaction> recentTransactions = transactionService.getRecentTransactions();
        return ResponseEntity.ok(recentTransactions);
    }





}
