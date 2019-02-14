package com.example.library.model;
/*
Author: BeGieU
Date: 03.02.2019
*/

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Book extends BaseEntity
{

    private String name;

    @Lob
    private String description;

    private String publisher;

    @Lob
    private Byte[] image;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private Set<Comment> commentSet = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authorSet = new HashSet<>();

    public Book addAuthor(Author author) throws IllegalArgumentException
    {
        if (author == null)
            throw new IllegalArgumentException("author null");

        author.getBookSet().add(this);
        authorSet.add(author);

        return this;

    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDesctiption()
    {
        return description;
    }

    public void setDesctiption(String desctiption)
    {
        this.description = desctiption;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public Byte[] getImage()
    {
        return image;
    }

    public void setImage(Byte[] image)
    {
        this.image = image;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Set<Author> getAuthorSet()
    {
        return authorSet;
    }

    public void setAuthorSet(Set<Author> authorSet)
    {
        this.authorSet = authorSet;
    }

}
