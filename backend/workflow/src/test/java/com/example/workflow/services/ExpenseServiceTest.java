package com.example.workflow.services;

import com.example.workflow.entities.Expense;
import com.example.workflow.entities.User;
import com.example.workflow.enums.ExpenseStatus;
import com.example.workflow.repositories.ExpenseRepository;
import com.example.workflow.repositories.UserRepository;
import com.example.workflow.repositories.ExpenseActionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ExpenseActionRepository expenseActionRepository;

    @InjectMocks
    private ExpenseService expenseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock Security Context
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("manager@test.com");

        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void shouldApproveExpenseWhenStatusIsPending() {

        Expense expense = new Expense();
        expense.setId(1L);
        expense.setStatus(ExpenseStatus.PENDING);

        User manager = new User();
        manager.setId(10L);
        manager.setEmail("manager@test.com");

        when(expenseRepository.findById(1L))
                .thenReturn(Optional.of(expense));

        when(userRepository.findByEmail("manager@test.com"))
                .thenReturn(Optional.of(manager));

        when(expenseRepository.save(any(Expense.class)))
                .thenReturn(expense);

        Expense result = expenseService.approveExpense(1L);

        assertEquals(ExpenseStatus.APPROVED, result.getStatus());
    }
}
