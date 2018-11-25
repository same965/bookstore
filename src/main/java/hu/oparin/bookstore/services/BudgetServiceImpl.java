package hu.oparin.bookstore.services;

import hu.oparin.bookstore.models.Budget;
import hu.oparin.bookstore.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {
    private TransactionService transactionService;

    @Autowired
    public BudgetServiceImpl (TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public List<Budget> createYearBudget() {
        List<Budget> budgets = new LinkedList<>();
        List<Integer> purchases = transactionService.budgetReport("purchase");
        List<Integer> sales = transactionService.budgetReport("sale");

        for (int i = 0; i < 12; i++) {
            Budget budget = new Budget();
            budget.setCosts(purchases.get(i));
            budget.setRevenues(sales.get(i));
            budgets.add(budget);
        }

        return budgets;
    }
}
