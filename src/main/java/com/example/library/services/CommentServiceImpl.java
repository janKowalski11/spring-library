package com.example.library.services;
/*
Author: BeGieU
Date: 15.02.2019
*/

import com.example.library.model.Book;
import com.example.library.model.Comment;
import com.example.library.repositories.BookRepository;
import com.example.library.repositories.CommentRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Component
public class CommentServiceImpl implements CommentService
{
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository)
    {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment findById(Long aLong)
    {
        return commentRepository.findById(aLong).orElse(null);
    }

    @Override
    public Set<Comment> findAll()
    {

        Set<Comment> commentSet = new HashSet<>();
        commentRepository.findAll().forEach(commentSet::add);

        return commentSet;
    }

    @Override
    @Transactional
    public Comment save(Comment obj)
    {

        return commentRepository.save(obj);
    }

    @Override
    public void deleteById(Long aLong)
    {
        commentRepository.deleteById(aLong);
    }
}
