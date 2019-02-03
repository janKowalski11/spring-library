package com.example.library.services;
/*
Author: BeGieU
Date: 03.02.2019
*/

import com.example.library.model.Author;
import com.example.library.repositories.AuthorRepository;

import java.util.Set;
import java.util.TreeSet;

public class AuthorServiceImpl implements AuthorService
{
    private final AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public Author findById(Long id)
    {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Set<Author> findAll()
    {
        Set<Author> authorSet = new TreeSet<>();
        repository.findAll().forEach(authorSet::add);

        return authorSet;
    }

    @Override
    public Author save(Author author)
    {
        return repository.save(author);
    }

    @Override
    public void deleteById(Long id)
    {
        repository.deleteById(id);
    }

    @Override
    public Author findAuthorByLastName(String lastName)
    {
        return repository.findAuthorByLastName(lastName);
    }
}
