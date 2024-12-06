package com.example.BookShopApp.controllers;

import com.example.BookShopApp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookshop")
public class MainPaigeController {

    private final BookService bookService;

    @Autowired
    public MainPaigeController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/main")
    public String mainPage(Model model) {
        model.addAttribute("bookData", bookService.getBooksData());
        return "index";
    }
}
