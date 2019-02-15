package com.example.library.converters;
/*
Author: BeGieU
Date: 11.02.2019
*/

import com.example.library.commands.BookCommand;
import com.example.library.model.Author;
import com.example.library.model.Book;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Set;

/* used for updating Book.
It's impossible to display standard Book class */
@Component
public class BookToBookCommand implements Converter<Book, BookCommand>
{

    private String getAuthorsLastName(Book book)
    {
        final Set<Author> authorSet = book.getAuthorSet();
        if (authorSet == null)
            return "";

        String authorsLastNames = "";
        for (Author author : authorSet)
        {
            authorsLastNames = authorsLastNames + author.getLastName() + ",";
            System.out.println("-----------> AUTHOR LAST NAME: " + authorsLastNames);
        }

        return authorsLastNames;
    }

    @Synchronized
    @Override
    public BookCommand convert(Book book)
    {
        if (book == null)
            throw new IllegalArgumentException("inserted bookCommand is null");

        final BookCommand bookCommand = new BookCommand();

        bookCommand.setId(book.getId());
        bookCommand.setPublisher(book.getPublisher());
        bookCommand.setDescription(book.getDescription());
        bookCommand.setName(book.getName());
        bookCommand.setAuthorLastName(this.getAuthorsLastName(book));

        return bookCommand;
    }
}
