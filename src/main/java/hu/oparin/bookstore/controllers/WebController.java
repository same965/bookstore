package hu.oparin.bookstore.controllers;

import hu.oparin.bookstore.models.Book;
import hu.oparin.bookstore.models.Budget;
import hu.oparin.bookstore.models.Customer;
import hu.oparin.bookstore.models.Quality;
import hu.oparin.bookstore.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class WebController {
    private ItemService itemService;
    private BookService bookService;
    private CustomerService customerService;
    private TransactionService transactionService;
    private BudgetService budgetService;

    @Autowired
    public WebController(ItemService itemService, BookService bookService, CustomerService customerService,
                         TransactionService transactionService, BudgetService budgetService) {
        this.itemService = itemService;
        this.bookService = bookService;
        this.customerService = customerService;
        this.transactionService = transactionService;
        this.budgetService = budgetService;
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
        model.addAttribute("customers", customerService.getCustomers());
        return "newitem";
    }

    @PostMapping("/addnewitem")
    public String addNewItem(@ModelAttribute(value = "quality") Quality quality,
                             @ModelAttribute(value = "cost") int cost,
                             @ModelAttribute(value = "customer") Long customerId,
                             @ModelAttribute(value = "id") Long bookId) {
        Long itemID = itemService.createAndGetID(bookId, quality, cost);
        String transactionType = "purchase";
        transactionService.create(customerService.getCustomerById(customerId), itemService.getItemById(itemID), transactionType);
        return "redirect:/inventory";
    }

    @PostMapping("/searchitem")
    public String searchItemByAuthor(@ModelAttribute(value = "author") String author, Model model) {
        model.addAttribute("items", itemService.searchInStock(author));
        return "searchitem";
    }

    @GetMapping("/edititem/{id}")
    public String editItem(@PathVariable("id") Long id, Model model) {
        model.addAttribute("item", itemService.getItemById(id));
        return "edit";
    }

    @PostMapping("/editeditem")
    public String updatedItem(@ModelAttribute("id") Long id,
                             @ModelAttribute("quality") Quality quality,
                             @ModelAttribute("price") int price) {
        itemService.updateItem(id, quality, price);
        return "redirect:/inventory";
    }

    @GetMapping("/sell/{id}")
    public String sellItem(@PathVariable("id") Long id, Model model) {
        model.addAttribute("item", itemService.getItemById(id));
        model.addAttribute("customers", customerService.getCustomers());
        return "sellitem";
    }

    @PostMapping("/sold")
    public String soldItem(@ModelAttribute("id") Long itemId,
                           @ModelAttribute("price") int price,
                           @ModelAttribute("customer") Long customerId) {
        itemService.getItemById(itemId).setPrice(price);
        itemService.getItemById(itemId).setSold(true);
        String transactionType = "sale";
        transactionService.create(customerService.getCustomerById(customerId), itemService.getItemById(itemId), transactionType);
        return "redirect:/inventory";
    }

    @GetMapping("/reports")
    public String reportBudget(Model model) {
        model.addAttribute("budgets", budgetService.createYearBudget());
        return "reports";
    }

}
