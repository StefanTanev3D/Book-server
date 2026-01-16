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
        new Book(1, "Cooking for Beginners", "Gosho"),
        new Book(2, "How to Code", "Pesho")
    ));
    @GetMapping
    public List<Book> getallBooks() {
        return books;
    };
    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book) {
        int nextId = books.stream().mapToInt(b -> b.getId()).max().orElse(0) + 1;
        book.setId(nextId);
        books.add(book);
        return book;
    }
    @PutMapping("/updateBook/{id}")
    public Book updateBook(@PathVariable("id") Integer id, @RequestBody Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                updatedBook.setId(id);
                books.set(i, updatedBook);
                return updatedBook;
            }
        }
        return null;
    }
    @DeleteMapping("/deleteBook/{id}")
    public void deleteBook(@PathVariable("id") Integer id) {
        for (int i = 0; i < books.size(); i++) {
            if (id == books.get(i).getId()) {
                books.remove(i);
                break;
            }
        }
    }
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }
}