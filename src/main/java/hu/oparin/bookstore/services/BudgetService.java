package hu.oparin.bookstore.services;

import hu.oparin.bookstore.models.Budget;

import java.util.List;

public interface BudgetService {
    List<Budget> createYearBudget();
}
