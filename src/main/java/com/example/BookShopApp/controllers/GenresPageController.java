package com.example.BookShopApp.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookshop/genres")
public class GenresPageController {

    Logger logger = Logger.getLogger(GenresPageController.class);

    @GetMapping
    public String genres(){
        logger.info("***DEBUG GenresPageController");
        return "genres/index";
    }

}
