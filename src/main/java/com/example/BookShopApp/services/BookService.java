package com.example.BookShopApp.services;

import com.example.BookShopApp.dto.Book;
import com.example.BookShopApp.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class BookService {

    BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooksData() {
        return bookRepository.findAll();
    }

    //Получаем все книги конкретного автора
    private List<Book> getAuthorBooks(Long authorId) {
        return bookRepository.findBooksByAuthorId(authorId);
    }
}
