package com.book.list.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.book.list.model.Book;
import com.book.list.model.Author;
import com.book.list.repository.BookRepository;
import com.book.list.repository.AuthorRepository;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/books")
public class BookController {
@Autowired
    private BookRepository bookRepository;

@Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public List<Book> getallBooks() {
        return bookRepository.findAll();
    };
    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book) {
        book.setAuthor(authorRepository.findById(book.getAuthor().getId()).orElse(null));
        return bookRepository.save(book);
    }
    @PutMapping("/updateBook/{id}")
    public Book updateBook(@PathVariable("id") Integer id, @RequestBody Book updatedBook) {
        Book existingBook = bookRepository.findById(id).orElse(null);
            if (existingBook != null) {
                    updatedBook.setAuthor(authorRepository.findById(updatedBook.getAuthor().getId()).orElse(null));
                updatedBook.setId(id);
                return bookRepository.save(updatedBook);
            }
        return null;
    }
    @DeleteMapping("/deleteBook/{id}")
    public void deleteBook(@PathVariable("id") Integer id) {
        bookRepository.deleteById(id);
    }
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id) {
        return bookRepository.findById(id).orElse(null);
    }
}