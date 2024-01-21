package com.api.book.controller;

import com.api.book.model.Book;
import com.api.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    //Get All Books
    @GetMapping("/book")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = this.bookService.getAllBooks();
        if(books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(books));
    }

    // Get Book By Id
    @GetMapping("/book/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable("bookId") Integer bookId) {
        Book book = this.bookService.getBookById(bookId);
        if(book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }

    // Add Single Book
    @PostMapping("/addBook")
    public ResponseEntity<Book> postBooks(@RequestBody Book book) {
        Book b = null;
        try {
            b = this.bookService.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(b);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("bookId") Integer bookId) {
        try{
            book = this.bookService.updateBook(book, bookId);
            if(book == null)
                throw new Exception();
            return ResponseEntity.ok().body(book);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
