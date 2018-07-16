package com.example.simplebooks.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simplebooks.exception.ResourceNotFoundException;
import com.example.simplebooks.model.Book;
import com.example.simplebooks.repository.BookRepository;

/**
 * Created by iuliantita on 16/07/18.
 */
@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @PostMapping("/books")
    public Book createBook(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable(value = "id") Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable(value = "id") Long bookId,
                                           @Valid @RequestBody Book bookDetails) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));

        book.setTitle(bookDetails.getTitle());
        book.setDescription(bookDetails.getDescription());

        Book updatedBook = bookRepository.save(book);
        return updatedBook;
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));

        bookRepository.delete(book);

        return ResponseEntity.ok().build();
    }
}
