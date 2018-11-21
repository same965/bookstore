package hu.oparin.bookstore.repositories;

import hu.oparin.bookstore.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Book findByAuthorAndTitle(String author, String title);
    List<Book> findAllByAuthorIsContaining(String author);
}
