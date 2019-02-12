package com.example.library.services;
/*
Author: BeGieU
Date: 03.02.2019
*/

import com.example.library.model.Author;
import com.example.library.model.Book;

import java.util.Set;

public interface BookService extends BasicCrudService<Book, Long>
{
    Book findByName(String name);

    Set<Book> findAllByNameLike(String name);
}
