package com.example.BookShopApp.services;

import com.example.BookShopApp.dto.Author;
import com.example.BookShopApp.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public AuthorService(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    //Возвращает всех автором целиком
    public List<Author> getAuthorsData() {
        String sql = "select * from authors order by \"name\"";
        List<Author> authors = jdbcTemplate.query(sql, (ResultSet rs, int rowNum) ->{
            Author author = new Author();
            author.setId(rs.getLong("id"));
            author.setName(rs.getString("name"));
            author.setBio(rs.getString("biography"));
            author.setBooks(getAuthorBooks(author.getId()));
            return author;
        });
        return new ArrayList<>(authors);
    }

    //Получаем все книги конкретного автора
    private List<Book> getAuthorBooks(Long authorId) {
        String sql = """
        select b.id, b.title, b.price, b.price_old, a."name" from books b
        left join authors a on b.author_id = a.id
        where author_id =:authorId;
        """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("authorId", authorId);
        List<Book> books = namedParameterJdbcTemplate.query(sql, params, (ResultSet rs, int rowNum) ->{
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("name"));
            book.setPrice(rs.getString("price"));
            book.setPriceold(rs.getString("price_old"));
            return book;
        });
        return new ArrayList<>(books);
    }

    //Группируем авторов по первой букве
    public Map<Character, List<Author>> getAuthorsGroupedByFirstLetter() {
        List<Author> authors = getAuthorsData(); // Метод получения списка авторов

        return authors.stream()
                .collect(Collectors.groupingBy(author -> author.getName().charAt(0))); // Группировка по первой букве
    }

    public Author getAuthorById(Long authorId) {
        String sql = "SELECT * FROM authors WHERE id = :authorId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("authorId", authorId);
        try {
            return namedParameterJdbcTemplate.queryForObject(sql, params, new AuthorRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private class AuthorRowMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            Author author = new Author();
            author.setId(rs.getLong("id"));
            author.setName(rs.getString("name"));
            author.setBio(rs.getString("biography"));
            author.setBooks(getAuthorBooks(author.getId()));
            return author;
        }
    }
}
