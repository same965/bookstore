package hu.oparin.bookstore.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 1)
    private String name;

    @Email(message = "Invalid email address")
    private String email;

    public Customer() {
    }

    public Customer(@Size(min = 1) String name, @Email(message = "Invalid email address") String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
