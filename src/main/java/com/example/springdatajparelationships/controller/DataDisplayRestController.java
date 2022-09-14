package com.example.springdatajparelationships.controller;

import com.example.springdatajparelationships.database.entity.Author;
import com.example.springdatajparelationships.database.entity.Book;
import com.example.springdatajparelationships.database.entity.Category;
import com.example.springdatajparelationships.database.entity.Photo;
import com.example.springdatajparelationships.database.repository.BookRepository;
import io.github.benas.randombeans.api.EnhancedRandom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class DataDisplayRestController
{
    @Autowired
    BookRepository bookRepository;
    @Autowired
    EnhancedRandom enhancedRandom;

    @GetMapping(value = "/data")
    public ResponseEntity<Object> getData()
    {
        long nextLong1 = enhancedRandom.nextLong();
        String nextObject = enhancedRandom.nextObject(String.class);
        String nextObject2 = enhancedRandom.nextObject(String.class);
        Book book = new Book();
        book.setTitle(nextObject);
        book.setIsbn(nextLong1);

        Author author = new Author();
        author.setFirstName("Kishore");
        author.setLastName("Madugula");
        author.setBirthDate(new Date(1978, 05, 10));
        List<Author> authorList = new ArrayList<>();
        authorList.add(author);

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        book.setAuthors(authorList);
        author.setBooks(bookList);

        Category category = new Category();
        category.setBooks(bookList);
        category.setCategory(nextObject2);
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);

        book.setCategories(categoryList);

        Photo photo = new Photo();
        photo.setBook(book);
        book.setPhoto(photo);

        Book savedBook = bookRepository.save(book);

        savedBook.getAuthors().forEach(x ->
        {
            log.info("----------------------- Authors -------------------------");
            log.info("Id {} first name: {} last name: {}", x.getId(), x.getFirstName(), x.getLastName());
        });

        savedBook.getCategories().forEach(x ->
        {
            log.info("----------------------- Categories -------------------------");
            log.info("Category {} Books size {}", x.getCategory(), x.getBooks().size());
        });
        log.info("----------------------- Photo ----------------------------------");
        log.info("Photo Id {} ", savedBook.getPhoto().getId());

        log.info("----------------------- Books ----------------------------------");
        log.info("Book Id {} Book Title {}", savedBook.getId(), savedBook.getTitle());
        log.info("----------------------- END -------------------------------------");

        return new ResponseEntity<>(savedBook.getTitle(), HttpStatus.OK);
    }

}
