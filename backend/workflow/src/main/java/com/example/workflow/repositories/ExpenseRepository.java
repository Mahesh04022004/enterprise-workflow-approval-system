package com.example.workflow.repositories;

import com.example.workflow.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.workflow.enums.ExpenseStatus;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByStatus(ExpenseStatus status);
}
