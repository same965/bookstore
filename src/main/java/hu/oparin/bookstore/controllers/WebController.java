package hu.oparin.bookstore.controllers;

import hu.oparin.bookstore.models.Book;
import hu.oparin.bookstore.services.BookService;
import hu.oparin.bookstore.services.CustomerService;
import hu.oparin.bookstore.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {
    private ItemService itemService;
    private BookService bookService;
    private CustomerService customerService;

    @Autowired
    public WebController(ItemService itemService, BookService bookService, CustomerService customerService) {
        this.itemService = itemService;
        this.bookService = bookService;
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String mainPage(){
        return "index";
    }

    @GetMapping("/inventory")
    public String inventory(Model model) {
        model.addAttribute("items", itemService.getItems());
        return "inventory";
    }

    @GetMapping("/newbook")
    public String newBookPage() {
        return "newbook";
    }

    @PostMapping("/addnewbook")
    public String addNewBook(@ModelAttribute(value = "author") String author,
                                @ModelAttribute(value = "title") String title,
                                @ModelAttribute(value = "historicalPrice") int historicalPrice,
                                @ModelAttribute(value = "rarity") int rarity,
                                @ModelAttribute(value = "liquidity") int liquidity,
                                @ModelAttribute(value = "domain") String domain) {
        bookService.create(author, title, historicalPrice, rarity, liquidity, domain);
        return "redirect:/book/" + bookService.getId(author, title);
    }

    @GetMapping("/book/{id}")
    public String bookPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "book";
    }

    @GetMapping("/customer")
    public String customerPage(Model model) {
        model.addAttribute("customers", customerService.getCustomers());
        return "customer";
    }

    @GetMapping("/newcustomer")
    public String newCustomerPage() {
        return "newcustomer";
    }

    @PostMapping("/addnewcustomer")
    public String addNewCustomer(@ModelAttribute(value = "name") String name,
                                 @ModelAttribute(value = "email") String email) {
        customerService.create(name, email);
        return "redirect:/customer";
    }

    @GetMapping("/purchase")
    public String purchasePage() {
         return "purchase";
    }

    @PostMapping("/purchase/book")
    public String chooseBook(@ModelAttribute(value = "author") String author, Model model) {
        model.addAttribute("books", bookService.booksByAuthor(author));
        model.addAttribute("search", bookService.checkAuthor(author));
        return "searchbook";
    }

    @GetMapping("/purchase/book/{id}")
    public String purchaseBook(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        model.addAttribute("recommendation", bookService.calculateRecommendedPrice(id));
        return "newitem";
    }

    @PostMapping("/addnewitem")
    public String addNewItem(@ModelAttribute(value = "quality") int quality,
                             @ModelAttribute(value = "cost") int cost,
                             @ModelAttribute(value = "id") Long id) {
        itemService.create(id, quality, cost);
        return "redirect:/inventory";
    }
}
