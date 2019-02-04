package com.example.library.services;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.repositories.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookServiceImplTest
{
    @Mock
    private BookRepository bookRepository;

    private BookService bookService;

    private Book book;
    private final Long BOOK_ID = 1L;


    @Before
    public void setUp() throws Exception
    {
        book = new Book();
        book.setId(BOOK_ID);

        MockitoAnnotations.initMocks(this);
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    public void findByName()
    {
        final String BOOK_NAME = "name";
        book.setName(BOOK_NAME);

        when(bookRepository.findByName(BOOK_NAME)).thenReturn(book);

        assertNotNull(bookService.findByName(BOOK_NAME));
        assertEquals(BOOK_NAME, bookService.findByName(BOOK_NAME).getName());
    }

    @Test
    public void findById()
    {
        when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.of(book));
        assertNotNull(bookService.findById(BOOK_ID));
    }

    @Test
    public void testFindByIdNull()
    {
        when(bookRepository.findById(null)).thenReturn(Optional.empty());
        assertNull(bookService.findById(null));
    }

    @Test
    public void findAll()
    {
        Set<Book> bookSet = new TreeSet<>();
        bookSet.add(book);

        Book book2 = new Book();
        book2.setId(2L);

        bookSet.add(book2);

        when(bookRepository.findAll()).thenReturn(bookSet);

        assertNotNull(bookService.findAll());
        assertEquals(2, bookService.findAll().size());
    }

    @Test
    public void save()
    {
        when(bookRepository.save(any())).thenReturn(book);


        assertEquals(book, bookService.save(book));
    }

    @Test
    public void deleteById()
    {
        bookService.deleteById(1L);
        verify(bookRepository).deleteById(1L);
    }
}