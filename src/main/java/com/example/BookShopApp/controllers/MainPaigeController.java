package com.example.BookShopApp.controllers;

import com.example.BookShopApp.services.BookService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookshop/main")
public class MainPaigeController {

    Logger logger = Logger.getLogger(MainPaigeController.class);
    private final BookService bookService;

    @Autowired
    public MainPaigeController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String mainPage(Model model) {
        model.addAttribute("bookData", bookService.getBooksData());
        return "index";
    }


}
