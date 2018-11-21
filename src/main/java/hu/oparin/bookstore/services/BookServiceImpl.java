package hu.oparin.bookstore.services;

import hu.oparin.bookstore.models.Book;
import hu.oparin.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public void create(String author, String title, int historicalPrice, int rarity, int liquidity, String domain) {
        bookRepository.save(new Book(author, title, historicalPrice, rarity, liquidity, domain));
    }

    @Override
    public Long getId(String author, String title) {
        return bookRepository.findByAuthorAndTitle(author, title).getId();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public boolean checkAuthor(String author) {
        if (bookRepository.findAllByAuthorIsContaining(author) != null) {
            return true;
        }
        return false;
    }

    @Override
    public List<Book> booksByAuthor(String author) {
        List<Book> books = new ArrayList<>();

        if (checkAuthor(author)) {
            bookRepository.findAllByAuthorIsContaining(author).forEach(books::add);
        }

        return books;
    }
}
