package com.example.library.controllers;
/*
Author: BeGieU
Date: 05.02.2019
*/

import com.example.library.model.Book;
import com.example.library.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.TreeSet;

@Controller
@RequestMapping("/books")
public class BookController
{
    private final BookService bookService;

    public BookController(BookService bookService)
    {
        this.bookService = bookService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder)
    {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping({"","/"})
    public String listBooks(Model model)
    {
        Set<Book> sortedBooks = new TreeSet<>(bookService.findAll());
        model.addAttribute("books", sortedBooks);

        return "book/booksPage";
    }
    @GetMapping("/new")
    public String initCreationForm(Model model)
    {
        model.addAttribute("book",new Book());
        return "book/createOrUpdateBookForm";
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid @ModelAttribute("book") Book book,
                                      BindingResult bindingResult)
    {
        return null;
    }


}
