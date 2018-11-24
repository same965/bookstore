package hu.oparin.bookstore.services;

import hu.oparin.bookstore.models.Customer;
import hu.oparin.bookstore.models.Item;

public interface TransactionService {
    void create(Customer customer, Item item, String transactionType);
}
