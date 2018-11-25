package hu.oparin.bookstore.services;

import hu.oparin.bookstore.models.Customer;
import hu.oparin.bookstore.models.Item;
import hu.oparin.bookstore.models.Transaction;

import java.util.List;

public interface TransactionService {
    void create(Customer customer, Item item, String transactionType);
    List<Integer> budgetReport(String type);
}
