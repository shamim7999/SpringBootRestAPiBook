package com.api.book.dao;
import com.api.book.model.Book;
import org.springframework.data.repository.CrudRepository;
public interface BookRepository extends CrudRepository<Book, Integer>{

}
