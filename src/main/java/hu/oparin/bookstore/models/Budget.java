package hu.oparin.bookstore.models;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Budget {
    private Integer costs;
    private Integer revenues;

    public Budget() {
    }

    public Budget(Integer costs, Integer revenues) {
        this.costs = costs;
        this.revenues = revenues;
    }

    public Integer getCosts() {
        return costs;
    }

    public void setCosts(Integer costs) {
        this.costs = costs;
    }

    public Integer getRevenues() {
        return revenues;
    }

    public void setRevenues(Integer revenues) {
        this.revenues = revenues;
    }
}
