package com.example.workflow.repositories;

import com.example.workflow.entities.ExpenseAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseActionRepository extends JpaRepository<ExpenseAction, Long> {
}
