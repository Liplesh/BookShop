package com.example.BookShopApp.controllers;

import com.example.BookShopApp.services.AuthorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bookshop/authors")
public class AuthorsPageController {

    Logger logger = Logger.getLogger(AuthorsPageController.class);
    private AuthorService authorService;

    @Autowired
    public AuthorsPageController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String authors(Model model) {
        model.addAttribute("authorData", authorService.getAuthorsData());
//        logger.info("***DEBUG AuthorsPageController");
        return "authors/index";
    }

    @PostMapping("/slug")
    public String authorSlug(@RequestParam(value = "authorId") int authorid, Model model){
        model.addAttribute("authorBio", authorService.getAuthorBio(authorid));
        return "authors/slug";
    }
}
