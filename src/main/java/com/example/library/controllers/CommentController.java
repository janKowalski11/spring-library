package com.example.library.controllers;
/*
Author: BeGieU
Date: 14.02.2019
*/

import com.example.library.model.Comment;
import com.example.library.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book/{bookId}/comment")
public class CommentController
{

    private final BookService bookService;

    public CommentController(BookService bookService)
    {
        this.bookService = bookService;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model, @PathVariable Long bookId)
    {
        //prevents from setting wrong relation, between comment and  book
        if (bookService.findById(bookId) == null)
            throw new RuntimeException("BOOK NOT FOUND FOR ID: " + bookId);

        final Comment comment = new Comment();
        comment.addBook(bookService.findById(bookId));

        model.addAttribute("comment", comment);

        return "book/comment/createOrUpdateCommentForm";
    }
}
