package com.example.library.converters;

import com.example.library.commands.BookCommand;
import com.example.library.model.Author;
import com.example.library.model.Book;

import com.example.library.services.AuthorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookCommandToBookTest
{
        @Autowired
    private BookCommandToBook bookCommandToBook;

    @Before
    public void setUp() throws Exception
    {

    }

    @Test
    public void convert()
    {
        final String authorLastNames = "Mickiewicz,Zulczyk";

        BookCommand bookCommand = new BookCommand();
        bookCommand.setDescription("ESSSSSA");
        bookCommand.setName("CSACASCAS");
        bookCommand.setPublisher("SAsasasasas");
        bookCommand.setAuthorLastName(authorLastNames);

        final Book convertedBook = bookCommandToBook.convert(bookCommand);
        Set<Author> convertedAuthors = convertedBook.getAuthorSet();
        convertedAuthors.forEach(author ->
        {
            System.out.println(author.getLastName());
        });


    }

}