package com.example.library.controllers;
/*
Author: BeGieU
Date: 05.02.2019
*/

import com.example.library.commands.BookCommand;
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
@RequestMapping("/book")
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

    @GetMapping({"", "/"})
    public String listBooks(Model model)
    {
        Set<Book> sortedBooks = new TreeSet<>(bookService.findAll());
        model.addAttribute("books", sortedBooks);

        return "book/booksPage";
    }

    @GetMapping("/{bookId}/show")
    public String showBook(Model model, @PathVariable Long bookId)
    {
        model.addAttribute("book", bookService.findById(bookId));
        return "book/show";
    }

    @GetMapping("/new")
    public String initCreationForm(Model model)
    {
        model.addAttribute("book", new BookCommand());
        return "book/createOrUpdateBookForm";
    }

    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(@Valid @ModelAttribute("book") BookCommand book,
                                      BindingResult bindingResult)
    {
        return null;
    }


}
