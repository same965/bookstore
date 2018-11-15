package hu.oparin.bookstore.controllers;

import hu.oparin.bookstore.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    private ItemService itemService;

    @Autowired
    public WebController(ItemService itemService) {
        this.itemService = itemService;
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
}
