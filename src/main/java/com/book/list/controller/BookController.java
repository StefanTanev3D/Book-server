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
        Long nextId = books.stream().mapToLong(b -> b.getId()).max().orElse(0) + 1;
        book.setId(nextId);
        books.add(book);
        return book;
    }
    @PutMapping("/updateBook/{id}")
    public Book updateBook(@PathVariable("id") String id, @RequestBody Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().toString().equals(id)) {
                updatedBook.setId(Long.parseLong(id));
                books.set(i, updatedBook);
                return updatedBook;
            }
        }
        return null;
    }
    @DeleteMapping("/deleteBook/{id}")
    public void deleteBook(@PathVariable("id") String id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().toString().equals(id)) {
                books.remove(i);
                break;
            }
        }
    }
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}