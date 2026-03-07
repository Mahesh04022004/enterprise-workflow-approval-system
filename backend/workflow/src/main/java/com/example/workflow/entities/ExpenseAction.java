package com.example.workflow.entities;

import com.example.workflow.enums.ExpenseActionType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "expense_actions")
public class ExpenseAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "expense_id", nullable = false)
    private Expense expense;

    @ManyToOne
    @JoinColumn(name = "performed_by", nullable = false)
    private User performedBy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExpenseActionType actionType;

    private String comment;

    private LocalDateTime timestamp;

    public ExpenseAction() {}

    @PrePersist
    public void prePersist() {
        this.timestamp = LocalDateTime.now();
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public User getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(User performedBy) {
        this.performedBy = performedBy;
    }

    public ExpenseActionType getActionType() {
        return actionType;
    }

    public void setActionType(ExpenseActionType actionType) {
        this.actionType = actionType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}