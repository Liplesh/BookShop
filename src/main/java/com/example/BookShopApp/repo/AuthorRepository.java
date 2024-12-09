package com.example.BookShopApp.repo;

import com.example.BookShopApp.dto.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Author findAuthorById(Long id);
}
