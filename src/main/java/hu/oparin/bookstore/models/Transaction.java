package hu.oparin.bookstore.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany
    @JoinTable(name = "item")
    private List<Item> items;

    private String type;
}
