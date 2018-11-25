package hu.oparin.bookstore.services;

import hu.oparin.bookstore.models.Customer;
import hu.oparin.bookstore.models.Item;
import hu.oparin.bookstore.models.Transaction;
import hu.oparin.bookstore.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    @Override
    public List<Integer> budgetReport(String type) {
        List<Integer> sum = new LinkedList<>();
        List<Transaction> transactions = new ArrayList<>();
        transactionRepository.findAllByType(type).forEach(transactions::add);

        for (int i = 1; i <= 12; i++) {
            Integer price = 0;

            for (Transaction transaction : transactions) {
                if (transaction.getDate().getMonthValue() == i) {
                    if (type.equals("purchase")) {
                        price += transaction.getItem().getCost();
                    } else if (type.equals("sale")) {
                        price += transaction.getItem().getPrice();
                    }
                }
            }
            sum.add(price);
        }
        return sum;
    }
}
