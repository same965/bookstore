package hu.oparin.bookstore.repositories;

import hu.oparin.bookstore.models.Item;
import hu.oparin.bookstore.models.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findAllByItem(Item item);
}
