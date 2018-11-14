package hu.oparin.bookstore.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "transaction_item",
               joinColumns = {@JoinColumn(name = "transaction_id")},
               inverseJoinColumns = {@JoinColumn(name = "item_id")})
    private Set<Item> items;

    private String type;
    private LocalDate date;

    public Transaction() {
        this.date = LocalDate.now();
    }

    public Transaction(Customer customer, Set<Item> items, String type) {
        this.customer = customer;
        this.items = items;
        this.type = type;
        this.date = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
