package hu.oparin.bookstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String mainPage(){
        return "index";
    }
}
