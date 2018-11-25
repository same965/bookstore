package hu.oparin.bookstore.services;

import hu.oparin.bookstore.models.Item;
import hu.oparin.bookstore.models.Quality;

import java.util.List;

public interface ItemService {
    List<Item> getItems();
    void create(Long id, Quality quality, int cost);
    List<Item> searchInStock(String author);
    Item getItemById(Long id);
    void updateItem(Long id, Quality quality, int price);
    Long createAndGetID(Long id, Quality quality, int cost);
}
