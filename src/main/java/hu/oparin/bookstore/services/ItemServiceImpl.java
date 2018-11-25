package hu.oparin.bookstore.services;

import hu.oparin.bookstore.models.Book;
import hu.oparin.bookstore.models.Item;
import hu.oparin.bookstore.models.Quality;
import hu.oparin.bookstore.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private BookService bookService;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, BookService bookService) {
        this.itemRepository = itemRepository;
        this.bookService = bookService;
    }

    @Override
    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        itemRepository.findAll().forEach(items::add);
        return  items;
    }

    @Override
    public void create(Long id, Quality quality, int cost) {
        Book book = bookService.getBookById(id);
        itemRepository.save(new Item(book, quality, 0, cost, false));
    }

    @Override
    public Long createAndGetID(Long id, Quality quality, int cost) {
        Item item = new Item();
        Book book = bookService.getBookById(id);
        item.setBook(book);
        item.setQuality(quality);
        item.setCost(cost);
        itemRepository.save(item);
        return item.getId();
    }

    @Override
    public List<Item> searchInStock(String author) {
        List<Book> books = bookService.booksByAuthor(author);

        List<Item> items = new ArrayList<>();

        for (Book book : books) {
            List<Item> foundItems = itemRepository.findAllByBook(book);
            for (Item founditem : foundItems) {
                if (!founditem.isSold()) {
                    items.add(founditem);
                }
            }
        }

        return items;
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id).get();
    }

    @Override
    public void updateItem(Long id, Quality quality, int price) {
        Item item = getItemById(id);
        item.setPrice(price);
        item.setQuality(quality);
        itemRepository.save(item);
    }
}
