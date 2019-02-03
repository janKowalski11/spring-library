package com.example.library.services;
/*
Author: BeGieU
Date: 03.02.2019
*/

import com.example.library.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface AuthorService extends BasicCrudService<Author, Long>
{
    Author findAuthorByLastName(String lastName);
}
