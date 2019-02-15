package com.example.library.model;
/*
Author: BeGieU
Date: 14.02.2019
*/

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
public class Comment extends BaseEntity
{

    @NotNull
    @DateTimeFormat(pattern = "dd-mm-yy")
    private LocalDate whenAdded;

    @Lob
    @NotBlank
    private String content;

    @NotBlank
    private String user;

    @ManyToOne
    private Book book;

    public Comment addBook(Book book)
    {
        if (book == null)
            throw new IllegalArgumentException("book you are tyring to add comment to is null");

        book.getCommentSet().add(this);
        this.book = book;

        return this;
    }

    public Long getOwningBooksId()
    {
        return book.getId();
    }

    public LocalDate getWhenAdded()
    {
        return whenAdded;
    }

    public void setWhenAdded(LocalDate whenAdded)
    {
        this.whenAdded = whenAdded;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public Book getBook()
    {
        return book;
    }

    public void setBook(Book book)
    {
        this.book = book;
    }

}
