package com.example.library.commands;
/*
Author: BeGieU
Date: 07.02.2019
*/



import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class BookCommand
{
    private Long id;

    @Size(min = 3)
    private String name;

    @Lob
    @NotBlank
    @Size(min = 3)
    private String description;

    private String publisher;

    @Pattern(regexp = "^[A-Za-z,]*$")
    private String authorLastName;


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public String getAuthorLastName()
    {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName)
    {
        this.authorLastName = authorLastName;
    }
}
