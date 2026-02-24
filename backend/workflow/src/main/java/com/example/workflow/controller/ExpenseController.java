package com.example.workflow.controller;

import com.example.workflow.entities.Expense;
import com.example.workflow.services.ExpenseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/create/{userId}")
    public Expense createExpense(@PathVariable Long userId,
                                 @RequestBody Expense expense) {
        return expenseService.createExpense(userId, expense);
    }
}
