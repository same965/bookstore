package hu.oparin.bookstore.repositories;

import hu.oparin.bookstore.models.Book;
import hu.oparin.bookstore.models.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findAllByBook(Book book);
}
