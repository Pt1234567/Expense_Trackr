package com.expenseTracker.service.Impl;

import com.expenseTracker.entity.Expense;
import com.expenseTracker.repository.ExpenseRepository;
import com.expenseTracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Expense createExpense(Expense expense) {
         return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
}
