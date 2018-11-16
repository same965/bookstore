package hu.oparin.bookstore.services;

public interface BookService {
    void create(String author, String title, int historicalPrice, int rarity, int liquidity, String domain);
}
