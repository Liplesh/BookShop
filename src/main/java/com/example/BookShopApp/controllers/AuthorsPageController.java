package com.example.BookShopApp.controllers;

import com.example.BookShopApp.dto.Author;
import com.example.BookShopApp.services.AuthorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookshop/authors")
public class AuthorsPageController {

    Logger logger = Logger.getLogger(AuthorsPageController.class);
    private final AuthorService authorService;

    @Autowired
    public AuthorsPageController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String authors(Model model) {
        model.addAttribute("groupedAuthors", authorService.getAuthorsGroupedByFirstLetter());
        return "authors/index";
    }

    @GetMapping("/slug/{id}")
    public String authorSlug(@PathVariable Long id, Model model){
        Author authorById = authorService.getAuthorById(id);
        if (authorById == null) {
            return "authors/index";
        }
        logger.info(authorById.toString());
        model.addAttribute("author", authorById);
        return "authors/slug";
    }
}
