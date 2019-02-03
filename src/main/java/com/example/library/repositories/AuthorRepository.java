package com.example.library.repositories;
/*
Author: BeGieU
Date: 03.02.2019
*/

import com.example.library.model.Author;
import com.example.library.services.BasicCrudService;
import org.springframework.data.repository.CrudRepository;


public interface AuthorRepository extends CrudRepository<Author,Long>
{
    Author findAuthorByLastName(String lastName);
}
