package hu.oparin.bookstore.models;

import javax.persistence.*;

@Entity
public class Item {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private String quality;
    private int price;
    private int cost;
    private boolean isSold;

    public Item() {
    }

    public Item(Long id, Book book, String quality, int price, int cost, boolean isSold) {
        this.id = id;
        this.book = book;
        this.quality = quality;
        this.price = price;
        this.cost = cost;
        this.isSold = isSold;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }
}
