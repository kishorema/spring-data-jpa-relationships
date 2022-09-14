package com.example.springdatajparelationships;

import com.example.springdatajparelationships.database.entity.Author;
import com.example.springdatajparelationships.database.entity.Book;
import com.example.springdatajparelationships.database.entity.Category;
import com.example.springdatajparelationships.database.entity.Photo;
import com.example.springdatajparelationships.database.repository.BookRepository;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AuthorBookIntegrationTest
{
    @Autowired
    BookRepository bookRepository;
    @Autowired
    EnhancedRandom enhancedRandom;

    @Test
    void saveAuthorBook()
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
        Assertions.assertNotNull(savedBook);

    }
}
