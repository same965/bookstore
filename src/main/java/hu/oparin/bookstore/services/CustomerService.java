package hu.oparin.bookstore.services;

import hu.oparin.bookstore.models.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers();

    void create(String name, String email);
}
