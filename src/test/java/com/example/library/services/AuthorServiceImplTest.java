package com.example.library.services;

import com.example.library.model.Author;
import com.example.library.repositories.AuthorRepository;
import net.bytebuddy.description.modifier.Ownership;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthorServiceImplTest
{
    @Mock
    private AuthorRepository authorRepository;

    private AuthorService authorService;

    private Author author;
    private final long AUTHOR_ID = 1L;


    @Before
    public void setUp()
    {
        author = new Author();
        author.setId(AUTHOR_ID);

        MockitoAnnotations.initMocks(this);
        authorService = new AuthorServiceImpl(authorRepository);
    }

    @Test
    public void testFindById()
    {
        when(authorRepository.findById(AUTHOR_ID)).thenReturn(Optional.of(author));
        assertNotNull(authorService.findById(AUTHOR_ID));
    }

    @Test
    public void testFindByIdNull()
    {
        when(authorRepository.findById(null)).thenReturn(Optional.empty());
        assertNull(authorService.findById(null));
    }

    @Test
    public void testFindAll()
    {
        Set<Author> authorSet = new TreeSet<>();
        authorSet.add(author);

        Author author2 = new Author();
        author2.setId(2L);

        authorSet.add(author2);

        when(authorRepository.findAll()).thenReturn(authorSet);

        assertNotNull(authorService.findAll());
        assertEquals(2, authorService.findAll().size());
    }

    @Test
    public void testSave()
    {
        when(authorRepository.save(any())).thenReturn(author);


        assertEquals(author,authorService.save(author));
    }

    @Test
    public void deleteById()
    {
        authorService.deleteById(1L);
        verify(authorRepository).deleteById(1L);
    }


}