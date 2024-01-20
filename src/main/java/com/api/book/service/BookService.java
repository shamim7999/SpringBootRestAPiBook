package com.api.book.service;

import com.api.book.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    public static List<Book> bookList = new ArrayList<>();
    static {
        bookList.add(new Book(1, "C++", "Bjarne"));
        bookList.add(new Book(2, "C", "Dennis"));
        bookList.add(new Book(3, "Java", "Gosling"));
    }
    public List<Book> getAllBooks() {
        return bookList;
    }
    public Book getBookById(int id) {
        Book book = bookList.stream().filter(b -> b.getId() == id).findFirst().get();
        return book;
    }

    public Book addBook(Book book) {
        bookList.add(book);
        return book;
    }

    public List<Book> deleteBook(int id) {
        bookList = bookList.stream().filter(book -> book.getId()!=id).collect(Collectors.toList());
        return bookList;
    }

    public void updateBook(Book book, int id) {
        bookList = bookList.stream().map(b -> {
            if(b.getId() == id)
                b = book;
            return b;
        }).collect(Collectors.toList());
    }
}
