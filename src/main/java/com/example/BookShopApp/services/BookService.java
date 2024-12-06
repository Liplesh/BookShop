package com.example.BookShopApp.services;

import com.example.BookShopApp.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class BookService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksData() {
        String sql = "select * from books";
        List<Book> books = jdbcTemplate.query(sql, (ResultSet rs, int rowNum) ->{
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setPrice(rs.getString("price"));
            book.setPriceold(rs.getString("priceOld"));
            return book;
        });
        Logger.getLogger(BookService.class.getName()).info("*****DEBUG books size: " + books.size());
        return new ArrayList<>(books);
    }
}
