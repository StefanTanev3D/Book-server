package com.book.list.controller;

import org.springframework.web.bind.annotation.*;

import com.book.list.model.Book;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/books")
public class BookController {
    private List<Book> books = new ArrayList<>(List.of(
        new Book(1L, "Cooking for Beginners", "Gosho"),
        new Book(2L, "How to Code", "Pesho")
    ));
    @GetMapping
    public List<Book> getallBooks() {
        return books;
    };
    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}