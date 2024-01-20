package com.api.book.controller;

import com.api.book.model.Book;
import com.api.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    //Get All Books
    @GetMapping("/book")
    public List<Book> getAllBooks() {
        return this.bookService.getAllBooks();
    }

    // Get Book By Id
    @GetMapping("/book/{bookId}")
    public Book getBookById(@PathVariable("bookId") Integer bookId) {
        return this.bookService.getBookById(bookId);
    }

    // Add Single Book
    @PostMapping("/addBook")
    public Book postBooks(@RequestBody Book book) {
        return this.bookService.addBook(book);
    }

    //Add Multiple Books
    @PostMapping("/addBooks")
    public List<Book> postBooks(@RequestBody List<Book> books) {
        for(Book b : books) {
            this.bookService.addBook(b);
        }
        return this.bookService.getAllBooks();
    }

    //Delete a Book
    @DeleteMapping("/book/{bookId}")
    public List<Book> deleteBook(@PathVariable("bookId") Integer bookId) {
        return this.bookService.deleteBook(bookId);
    }

    //Update a Book
    @PutMapping("/book/{bookId}")
    public void updateBook(@RequestBody Book book, @PathVariable("bookId") Integer bookId) {
        this.bookService.updateBook(book, bookId);
    }
}
