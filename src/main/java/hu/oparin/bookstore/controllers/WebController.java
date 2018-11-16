package hu.oparin.bookstore.controllers;

import hu.oparin.bookstore.models.Book;
import hu.oparin.bookstore.services.BookService;
import hu.oparin.bookstore.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {
    private ItemService itemService;
    private BookService bookService;

    @Autowired
    public WebController(ItemService itemService, BookService bookService) {
        this.itemService = itemService;
        this.bookService = bookService;
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

    @PostMapping("addnewbook")
    public String addNewBook(@ModelAttribute(value = "author") String author,
                                @ModelAttribute(value = "title") String title,
                                @ModelAttribute(value = "historicalPrice") int historicalPrice,
                                @ModelAttribute(value = "rarity") int rarity,
                                @ModelAttribute(value = "liquidity") int liquidity,
                                @ModelAttribute(value = "domain") String domain) {
        bookService.create(author, title, historicalPrice, rarity, liquidity, domain);
        return "redirect:/inventory";
    }
    
}
