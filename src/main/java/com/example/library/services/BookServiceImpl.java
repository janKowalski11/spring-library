package com.example.library.services;
/*
Author: BeGieU
Date: 04.02.2019
*/

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.repositories.BookRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Component
public class BookServiceImpl implements BookService
{
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository)
    {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book findByName(String name)
    {
        //
        return name.matches("[a-zA-Z]+") ? bookRepository.findByName(name) : null;
    }

    @Override
    public Book findById(Long id)
    {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Set<Book> findAll()
    {
        Set<Book> bookSet = new HashSet<>();
        bookRepository.findAll().forEach(bookSet::add);

        return bookSet;
    }

    @Override
    @Transactional
    public Book save(Book book)
    {
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id)
    {
        bookRepository.deleteById(id);
    }

    @Override
    public Set<Book> findAllByNameLike(String name)
    {
        return bookRepository.findAllByNameLike("%" + name + "%");
    }
}
