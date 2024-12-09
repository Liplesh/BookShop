package com.example.BookShopApp.services;

import com.example.BookShopApp.dto.Author;
import com.example.BookShopApp.dto.Book;
import com.example.BookShopApp.repo.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    //Возвращает всех автором целиком
    public List<Author> getAuthorsData() {
        return authorRepository.findAll();
    }

    //Группируем авторов по первой букве
    public Map<Character, List<Author>> getAuthorsGroupedByFirstLetter() {
        List<Author> authors = getAuthorsData(); // Метод получения списка авторов

        if (authors.isEmpty())
            return new HashMap<>();

        return authors.stream()
                .collect(Collectors.groupingBy(author -> author.getName().charAt(0))); // Группировка по первой букве
    }

    public Author getAuthorById(Long authorId) {
        return authorRepository.findAuthorById(authorId);
    }

}
