package com.library.book.service;

import com.library.book.model.Book;
import com.library.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Book not found with id: " + id));
    }

    public Book addBook(Book book) {
        book.setAvailable(true);
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updated) {
        Book existing = getBookById(id);
        existing.setTitle(updated.getTitle());
        existing.setGenre(updated.getGenre());
        existing.setAuthorId(updated.getAuthorId());
        return bookRepository.save(existing);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailableTrue();
    }

    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> searchByGenre(String genre) {
        return bookRepository.findByGenreIgnoreCase(genre);
    }

    public List<Book> getBooksByAuthor(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    // ✅ Called by Borrow Service to update availability
    public Book updateAvailability(Long id, Boolean available) {
        Book book = getBookById(id);
        book.setAvailable(available);
        return bookRepository.save(book);
    }
}