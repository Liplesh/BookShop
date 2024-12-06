package com.example.BookShopApp.services;

import com.example.BookShopApp.dto.Author;
import com.example.BookShopApp.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class AuthorService {

    private JdbcTemplate jdbcTemplate;
//    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public AuthorService(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
//        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Author> getAuthorsData() {
        String sql = "select * from author";
        List<Author> authors = jdbcTemplate.query(sql, (ResultSet rs, int rowNum) ->{
            Author author = new Author();
            author.setId(rs.getInt("id"));
            author.setName(rs.getString("title"));
            author.setBio(rs.getString("name"));
            author.setBooks(getAuthorBooks(author.getId()));
            return author;
        });
        return new ArrayList<>(authors);
    }

    //Получаем все книги конкретного автора
    private List<Book> getAuthorBooks(int authorId) {
        String sql = """
            select b.id, b.title, b.price, b.price_old, a."name" from books b
            left join authors a on b.author_id = a.id
            where author_id =:authorId;
        """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("authorId", authorId);
//        List<Book> books = namedParameterJdbcTemplate.query(sql, params, (ResultSet rs, int rowNum) ->{
//            Book book = new Book();
//            book.setId(rs.getInt("id"));
//            book.setTitle(rs.getString("title"));
//            book.setAuthor(rs.getString("name"));
//            book.setPrice(rs.getString("price"));
//            book.setPriceold(rs.getString("price_old"));
//            return book;
//        });
        List<Book> books = jdbcTemplate.query(sql, (ResultSet rs, int rowNum) ->{
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("name"));
            book.setPrice(rs.getString("price"));
            book.setPriceold(rs.getString("price_old"));
            return book;
        }, params);
        Logger.getLogger(BookService.class.getName()).info("*****DEBUG books size: " + books.size());
        return new ArrayList<>(books);
    }

    public Object getAuthorBio(int id) {
        String sql = "select biography from authors where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        Object bio = jdbcTemplate.queryForObject(sql, Object.class, params);
        if (Objects.isNull(bio)) {
            return "";
        } else {
            return bio.toString();
        }
    }
}
