package com.example.library.Converters;
/*
Author: BeGieU
Date: 08.02.2019
*/

import com.example.library.commands.BookCommand;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.services.AuthorService;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BookCommandToBook implements Converter<BookCommand, Book>
{

    private final AuthorService authorService;

    public BookCommandToBook(AuthorService authorService)
    {
        this.authorService = authorService;
    }

    private Set<Author> getAuthorSet(BookCommand bookCommand)
    {
        //todo test this, here is error

        String lastNames = bookCommand.getAuthorLastName();
        if (lastNames == null)
            return null;

        /* it's Necessary to find last name; */
        lastNames = lastNames + ',';

        Set<Author> authorSet = new HashSet<>();


        int beginIndex = 0;
        int endIndex;
        endIndex = lastNames.indexOf(',', beginIndex);
        while (endIndex != -1)
        {
            String foundLastName = lastNames.substring(beginIndex, endIndex);
            Author foundAuthor = authorService.findAuthorByLastName(foundLastName);
            authorSet.add(foundAuthor);

            beginIndex = endIndex;
            endIndex = lastNames.indexOf(',', beginIndex);
        }

        return authorSet;
    }

    @Synchronized
    @Override
    public Book convert(BookCommand bookCommand)
    {
        if (bookCommand == null)
            throw new IllegalArgumentException("inserted bookCommand is null");

        final Book book = new Book();
        book.setId(bookCommand.getId());
        book.setName(bookCommand.getName());
        book.setDescription(bookCommand.getDescription());
        book.setPublisher(bookCommand.getPublisher());
        book.setAuthorSet(this.getAuthorSet(bookCommand));

        return book;
    }

}
