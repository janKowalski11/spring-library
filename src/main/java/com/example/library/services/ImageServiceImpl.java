package com.example.library.services;
/*
Author: BeGieU
Date: 20.02.2019
*/

import com.example.library.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService
{
    private final BookService bookService;

    public ImageServiceImpl(BookService bookService)
    {
        this.bookService = bookService;
    }

    @Override
    public void SaveImageFile(Long bookId, MultipartFile file)
    {
        try
        {
            Book book = bookService.findById(bookId);
            Byte[] bytes = new Byte[file.getBytes().length];

            //bytes to Bytes
            int i = 0;
            for (byte b : file.getBytes())
            {
                bytes[i++] = b;
            }
            book.setImage(bytes);

            bookService.save(book);

        }
        catch (Exception e)
        {
            System.out.println("Error: "+ e);
            e.printStackTrace();
        }
    }
}

