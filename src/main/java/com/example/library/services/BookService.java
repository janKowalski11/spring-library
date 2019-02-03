package com.example.library.services;
/*
Author: BeGieU
Date: 03.02.2019
*/

import com.example.library.model.Author;
import com.example.library.model.Book;

public interface BookService extends BasicCrudService<Book, Long>
{
    Author findByName(String name);
}
