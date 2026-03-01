package com.example.workflow.controller;

import com.example.workflow.entities.Expense;
import com.example.workflow.payload.ApiResponse;
import com.example.workflow.services.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // EMPLOYEE creates expense for themselves
    @PreAuthorize("hasRole('EMPLOYEE')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Expense>> createExpense(
            @RequestBody Expense expense) {

        Expense savedExpense = expenseService.createExpense(expense);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Expense created successfully", savedExpense)
        );
    }

    // MANAGER approves
    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{expenseId}/approve")
    public ResponseEntity<ApiResponse<Expense>> approveExpense(
            @PathVariable Long expenseId) {

        Expense updatedExpense = expenseService.approveExpense(expenseId);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Expense approved successfully", updatedExpense)
        );
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{expenseId}/reject")
    public ResponseEntity<ApiResponse<Expense>> rejectExpense(
            @PathVariable Long expenseId) {

        Expense updatedExpense = expenseService.rejectExpense(expenseId);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Expense rejected successfully", updatedExpense)
        );
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/pending")
    public ResponseEntity<ApiResponse<List<Expense>>> getPendingExpenses() {
        List<Expense> pendingExpenses = expenseService.getPendingExpenses();
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Pending expenses fetched successfully", pendingExpenses)
        );
    }
}