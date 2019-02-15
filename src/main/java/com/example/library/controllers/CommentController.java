package com.example.library.controllers;
/*
Author: BeGieU
Date: 14.02.2019
*/

import com.example.library.model.Comment;
import com.example.library.services.BookService;
import com.example.library.services.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/book/{bookId}/comment")
@SessionAttributes("comment")
public class CommentController
{

    private final BookService bookService;
    private final CommentService commentService;

    private final static String COMMENT_FORM_URL = "book/comment/createOrUpdateCommentForm";

    public CommentController(BookService bookService, CommentService commentService)
    {
        this.bookService = bookService;
        this.commentService = commentService;
    }

    @InitBinder("comment")
    public void setAllowedFields(WebDataBinder dataBinder)
    {
        dataBinder.setDisallowedFields("id");
        dataBinder.setDisallowedFields("book");
        dataBinder.setDisallowedFields("whenAdded");
    }



    @GetMapping("/new")
    public String initCreationForm(Model model, @PathVariable Long bookId)
    {
        //prevents from setting wrong relation, between comment and  book
        if (bookService.findById(bookId) == null)
            throw new RuntimeException("BOOK NOT FOUND FOR ID: " + bookId);

        final Comment comment = new Comment();
        comment.addBook(bookService.findById(bookId));
        comment.setWhenAdded(LocalDate.now());

        model.addAttribute("comment",comment);

        return COMMENT_FORM_URL;
    }

    //TODO FIX UPDATE BUG
    @GetMapping("/{commentId}/update")
    public String initUpdateForm(Model model, @PathVariable Long commentId)
    {
        //this comment overrides the one from addCommentToModel method
        model.addAttribute("comment", commentService.findById(commentId));
        return COMMENT_FORM_URL;
    }


    @PostMapping("/saveOrUpdate")
    public String saveOrUpdateComment(@Valid @ModelAttribute Comment comment,
                                      BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return COMMENT_FORM_URL;
        }

        commentService.save(comment);
        return "redirect:/book/" + comment.getOwningBooksId() + "/show";

    }
}
