package hu.oparin.bookstore.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 1)
    private String author;

    @Size(min = 1)
    private String title;

    private int historicalPrice;
    private int rarity;
    private int liquidity;
    private String domain;

    public Book() {
    }

    public Book(@Size(min = 1) String author, @Size(min = 1) String title) {
        this.author = author;
        this.title = title;
    }

    public Book(@Size(min = 1) String author, @Size(min = 1) String title, int historicalPrice, int rarity, int liquidity, String domain) {
        this.author = author;
        this.title = title;
        this.historicalPrice = historicalPrice;
        this.rarity = rarity;
        this.liquidity = liquidity;
        this.domain = domain;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHistoricalPrice() {
        return historicalPrice;
    }

    public void setHistoricalPrice(int historicalPrice) {
        this.historicalPrice = historicalPrice;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public int getLiquidity() {
        return liquidity;
    }

    public void setLiquidity(int liquidity) {
        this.liquidity = liquidity;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
