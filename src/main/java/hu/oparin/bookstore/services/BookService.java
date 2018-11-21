package hu.oparin.bookstore.services;

import hu.oparin.bookstore.models.Book;

import java.util.List;

public interface BookService {
    void create(String author, String title, int historicalPrice, int rarity, int liquidity, String domain);
    Long getId(String author, String title);
    Book getBookById(Long id);
    boolean checkAuthor(String author);
    List<Book> booksByAuthor(String author);
    double calculateRecommendedPrice(Long id);
}
