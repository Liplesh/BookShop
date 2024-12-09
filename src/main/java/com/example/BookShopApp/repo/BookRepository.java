package com.example.BookShopApp.repo;

import com.example.BookShopApp.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findBooksByAuthorId(Long authorId);

//    @Query("from Book") //HQL
//    List<Book> customFindAllBooks();
}
