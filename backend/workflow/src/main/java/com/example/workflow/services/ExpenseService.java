package com.example.workflow.services;

import com.example.workflow.entities.Expense;
import com.example.workflow.entities.User;
import com.example.workflow.exceptions.BadRequestException;
import com.example.workflow.exceptions.ResourceNotFoundException;
import com.example.workflow.repositories.ExpenseRepository;
import com.example.workflow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import com.example.workflow.enums.ExpenseStatus;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository,
                          UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    public Expense createExpense(Long userId, Expense expense) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        expense.setUser(user);

        return expenseRepository.save(expense);
    }

    public Expense approveExpense(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));
        if (expense.getStatus() != ExpenseStatus.PENDING) {
            throw new BadRequestException("Expense already processed");
        }
        expense.setStatus(ExpenseStatus.APPROVED);
        return expenseRepository.save(expense);
    }

    public Expense rejectExpense(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));
        if (expense.getStatus() != ExpenseStatus.PENDING) {
            throw new BadRequestException("Expense already processed");
        }
        expense.setStatus(ExpenseStatus.REJECTED);
        return expenseRepository.save(expense);
    }
}
