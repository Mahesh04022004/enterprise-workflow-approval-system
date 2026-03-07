package com.example.workflow.services;

import com.example.workflow.entities.Expense;
import com.example.workflow.entities.User;
import com.example.workflow.exceptions.BadRequestException;
import com.example.workflow.exceptions.ResourceNotFoundException;
import com.example.workflow.repositories.ExpenseActionRepository;
import com.example.workflow.repositories.ExpenseRepository;
import com.example.workflow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import com.example.workflow.enums.ExpenseStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.workflow.entities.ExpenseAction;
import com.example.workflow.enums.ExpenseActionType;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final ExpenseActionRepository expenseActionRepository;
    public ExpenseService(ExpenseRepository expenseRepository,
                          UserRepository userRepository,ExpenseActionRepository expenseActionRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.expenseActionRepository = expenseActionRepository;
    }

    public Expense createExpense(Expense expense) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        expense.setUser(user);
        expense.setStatus(ExpenseStatus.PENDING);

        Expense savedExpense = expenseRepository.save(expense);
        ExpenseAction action = new ExpenseAction();
        action.setExpense(savedExpense);
        action.setPerformedBy(user);
        action.setActionType(ExpenseActionType.SUBMITTED);

        expenseActionRepository.save(action);

        return savedExpense;
    }

    public Expense approveExpense(Long expenseId) {

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));

        if (expense.getStatus() != ExpenseStatus.PENDING) {
            throw new BadRequestException("Expense already processed");
        }

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User approver = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        expense.setStatus(ExpenseStatus.APPROVED);

        Expense updatedExpense = expenseRepository.save(expense);

        ExpenseAction action = new ExpenseAction();
        action.setExpense(updatedExpense);
        action.setPerformedBy(approver);
        action.setActionType(ExpenseActionType.APPROVED);

        expenseActionRepository.save(action);

        return updatedExpense;
    }

    public Expense rejectExpense(Long expenseId) {

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));

        if (expense.getStatus() != ExpenseStatus.PENDING) {
            throw new BadRequestException("Expense already processed");
        }

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User approver = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        expense.setStatus(ExpenseStatus.REJECTED);

        Expense updatedExpense = expenseRepository.save(expense);

        ExpenseAction action = new ExpenseAction();
        action.setExpense(updatedExpense);
        action.setPerformedBy(approver);
        action.setActionType(ExpenseActionType.REJECTED);

        expenseActionRepository.save(action);

        return updatedExpense;
    }

    public List<Expense> getPendingExpenses() {
        return expenseRepository.findByStatus(ExpenseStatus.PENDING);
    }
}
