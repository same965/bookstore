package hu.oparin.bookstore.services;

import hu.oparin.bookstore.models.Customer;
import hu.oparin.bookstore.models.Item;
import hu.oparin.bookstore.models.Transaction;
import hu.oparin.bookstore.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl (TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void create(Customer customer, Item item, String transactionType) {
        if (transactionRepository.findAllByItem(item).size() <= 2) {
            transactionRepository.save(new Transaction(customer, item, transactionType));
        }
    }
}
