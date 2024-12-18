package com.expenseTracker.service;

import com.expenseTracker.entity.Expense;

import java.util.List;

public interface ExpenseService {

    Expense createExpense(Expense expense);

    List<Expense> getAllExpenses();

}
