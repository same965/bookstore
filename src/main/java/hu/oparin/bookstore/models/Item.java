package hu.oparin.bookstore.models;

import javax.persistence.*;

@Entity
public class Item {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    private String quality;
    private int price;
    private int cost;
    private boolean isSold;




}
