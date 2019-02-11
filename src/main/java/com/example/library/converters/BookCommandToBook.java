package com.example.library.converters;
/*
Author: BeGieU
Date: 08.02.2019
*/

import com.example.library.commands.BookCommand;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.services.AuthorService;
import com.example.library.services.AuthorServiceImpl;
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
        //todo write test fot this,

        String lastNames = bookCommand.getAuthorLastName();
        if (lastNames == null || lastNames.equals("") || lastNames.length() == 0)
            return null;

        int indexOfLastChar = lastNames.length() - 1;
        if (Character.isLetter(lastNames.charAt(indexOfLastChar)))
        {
            /*colonel on the end of string is compulsory for below while loop to work properly
            * so we add it if it is not present. Notice that this string is validated by
            * @valid and it passes validation when it contains letters and colonels only*/
            lastNames = lastNames + ',';
        }

        Set<Author> authorSet = new HashSet<>();

        int beginIndex = 0;
        int endIndex;
        endIndex = lastNames.indexOf(',', beginIndex);
        while (endIndex != -1)
        {
            String foundLastName = lastNames.substring(beginIndex, endIndex);

            Author foundAuthor = authorService.findAuthorByLastName(foundLastName);
            authorSet.add(foundAuthor);

            beginIndex = endIndex + 1;
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
