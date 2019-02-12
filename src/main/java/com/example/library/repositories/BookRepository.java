package com.example.library.repositories;
/*
Author: BeGieU
Date: 03.02.2019
*/

import com.example.library.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface BookRepository extends CrudRepository<Book, Long>
{
    Book findByName(String name);

    Set<Book> findAllByNameLike(String name);
}
