package com.example.workflow.controller;

import com.example.workflow.entities.Expense;
import com.example.workflow.payload.ApiResponse;
import com.example.workflow.services.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PutMapping("/{id}/approve")
    public ResponseEntity<ApiResponse<Expense>> approveExpense(
            @PathVariable Long id) {

        Expense updatedExpense = expenseService.approveExpense(id);

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
}