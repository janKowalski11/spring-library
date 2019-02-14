package com.example.library.controllers;
/*
Author: BeGieU
Date: 04.02.2019
*/

import com.example.library.services.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/author")
public class AuthorController
{
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService)
    {
        this.authorService = authorService;
    }

    @GetMapping("/{authorId}/show")
    public String showAuthor(Model model, @PathVariable Long authorId)
    {
        model.addAttribute(authorService.findById(authorId));
        return "author/show";
    }
}
