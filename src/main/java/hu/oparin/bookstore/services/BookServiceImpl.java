package hu.oparin.bookstore.services;

import hu.oparin.bookstore.models.Book;
import hu.oparin.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
