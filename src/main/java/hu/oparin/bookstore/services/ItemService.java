package hu.oparin.bookstore.services;

import hu.oparin.bookstore.models.Item;

import java.util.List;

public interface ItemService {
    List<Item> getItems();
}