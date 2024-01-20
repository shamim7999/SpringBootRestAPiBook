package com.api.book.service;

import com.api.book.dao.BookRepository;
import com.api.book.model.Author;
import com.api.book.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public List<Book> getAllBooks() {
        try {
            return (List<Book>) this.bookRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Book getBookById(int id) {
        try {
            return this.bookRepository.findById(id).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Book addBook(Book book) {
        try{
            this.bookRepository.save(book);
            return book;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Book> deleteBook(int id) {
        try {
            this.bookRepository.deleteById(id);
            return getAllBooks();
        } catch (Exception e) {
            return null;
        }

    }

    public Book updateBook(Book book, int id) {
        try {
            if(getBookById(id) == null)
                throw new Exception();
            Book b = null;
            Author a = null;
            b = getBookById(id);
            a = b.getAuthor();

            b.setId(id);
            if(book.getTitle()!=null)
                b.setTitle(book.getTitle());

            if(book.getAuthor().getName()!=null)
                a.setName(book.getAuthor().getName());

            if(book.getAuthor().getCity()!=null)
                a.setCity(book.getAuthor().getCity());

            b.setAuthor(a);

            return addBook(b);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
