package com.library.book.repository;

import com.library.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAvailableTrue();
    List<Book> findByGenreIgnoreCase(String genre);
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorId(Long authorId);
}