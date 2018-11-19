package hu.oparin.bookstore.services;

import hu.oparin.bookstore.models.Book;

public interface BookService {
    void create(String author, String title, int historicalPrice, int rarity, int liquidity, String domain);
    Long getId(String author, String title);
    Book getBookById(Long id);
}
