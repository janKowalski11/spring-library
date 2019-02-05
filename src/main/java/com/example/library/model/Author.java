package com.example.library.model;
/*
Author: BeGieU
Date: 03.02.2019
*/

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Author extends BaseEntity
{
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Lob
    @NotBlank
    @Size(min = 3)
    private String description;

    @Lob
    private Byte[] image;

    @ManyToMany(mappedBy = "authorSet")
    private Set<Book> bookSet = new HashSet<>();

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getDesctiption()
    {
        return description;
    }

    public void setDesctiption(String description)
    {
        this.description = description;
    }

    public Byte[] getImage()
    {
        return image;
    }

    public void setImage(Byte[] image)
    {
        this.image = image;
    }

    public Set<Book> getBookSet()
    {
        return bookSet;
    }

    public void setBookSet(Set<Book> bookSet)
    {
        this.bookSet = bookSet;
    }

}
