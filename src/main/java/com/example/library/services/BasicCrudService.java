package com.example.library.services;
/*
Author: BeGieU
Date: 03.02.2019
*/


import java.util.Set;

public interface BasicCrudService<T, ID>
{
    T findById(ID id);

    Set<T> findAll();

    T save(T obj);

    void deleteById(ID id);
}
