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
    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate whenAdded;

    @Lob
    @NotBlank
    private String content;

    @NotBlank
    private String user;

    @ManyToOne
    private Book book;

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
}
