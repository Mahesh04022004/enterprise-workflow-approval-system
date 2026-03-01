package com.example.workflow.controller;

import com.example.workflow.entities.Expense;
import com.example.workflow.payload.ApiResponse;
import com.example.workflow.services.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<ApiResponse<Expense>> createExpense(
            @PathVariable Long userId,
            @RequestBody Expense expense) {

        Expense savedExpense = expenseService.createExpense(userId, expense);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Expense created successfully", savedExpense)
        );
    }


    @PutMapping("/{expenseId}/approve/{approverId}")
    public ResponseEntity<ApiResponse<Expense>> approveExpense(
            @PathVariable Long expenseId,
            @PathVariable Long approverId) {

        Expense updatedExpense = expenseService.approveExpense(expenseId, approverId);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Expense approved successfully", updatedExpense)
        );
    }


    @PutMapping("/{id}/reject")
    public ResponseEntity<ApiResponse<Expense>> rejectExpense(
            @PathVariable Long id) {

        Expense updatedExpense = expenseService.rejectExpense(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Expense rejected successfully", updatedExpense)
        );
    }
    @GetMapping("/pending")
    public ResponseEntity<ApiResponse<List<Expense>>> getPendingExpenses() {
        List<Expense> pendingExpenses = expenseService.getPendingExpenses();
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Pending expenses fetched successfully", pendingExpenses)
        );
    }
}