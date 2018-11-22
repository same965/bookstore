package hu.oparin.bookstore.services;

import hu.oparin.bookstore.models.Item;

import java.util.List;

public interface ItemService {
    List<Item> getItems();
    void create(Long id, int quality, int cost);
    List<Item> getItemsByAuthor(String author);
    Item getItemById(Long id);
    void updateItem(Long id, int quality, int price);
}
