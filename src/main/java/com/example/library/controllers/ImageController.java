package com.example.library.controllers;

import com.example.library.model.Book;
import com.example.library.services.BookService;
import com.example.library.services.ImageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/*
Author: BeGieU
Date: 20.02.2019
*/
@Controller
@RequestMapping("/book/{bookId}")
public class ImageController
{
    private final BookService bookService;
    private final ImageService imageService;

    public ImageController(BookService bookService, ImageService imageService)
    {
        this.bookService = bookService;
        this.imageService = imageService;
    }

    @GetMapping("/image")
    public String showUploadForm(@PathVariable Long bookId, Model model)
    {
        model.addAttribute("book", bookService.findById(bookId));
        return "book/imageUploadForm";
    }

    @PostMapping("/image")
    public String uploadImage(@PathVariable Long bookId, @RequestParam("imageFile") MultipartFile file)
    {
        imageService.SaveImageFile(bookId, file);
        return "redirect:/book/" + bookId + "/show";
    }

    @GetMapping("/getImage")
    public void renderImageFromDb(@PathVariable Long bookId, HttpServletResponse response) throws IOException
    {
        Book book = bookService.findById(bookId);

        byte[] photoByteArr;
        if (book.getImage() != null)
        {
            photoByteArr = new byte[book.getImage().length];

            int i = 0;
            for (Byte wrappedByte : book.getImage())
            {
                photoByteArr[i++] = wrappedByte;//auto unboxing wrapped byte
            }
        }
        else
        {
            File defaultPhoto = new ClassPathResource("static/images/image_not_set.png").getFile();
            photoByteArr = Files.readAllBytes(defaultPhoto.toPath());
        }
        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(photoByteArr);
        IOUtils.copy(is, response.getOutputStream());

    }
}
