package com.expenseTracker.repository;

import com.expenseTracker.entity.RecurringExpense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecurringExpenseRepository extends JpaRepository<RecurringExpense,Long> {
}
