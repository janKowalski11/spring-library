package com.example.library.services;
/*
Author: BeGieU
Date: 03.02.2019
*/

import com.example.library.model.Author;

import java.util.Set;

public  interface BasicCrudService<T,ID>
{
    T findById(Long ID);

    Set<T> findAll();

    T save(Author T);

    void deleteById(Long ID);
}
