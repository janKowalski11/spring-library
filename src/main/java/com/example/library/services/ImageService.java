package com.example.library.services;
/*
Author: BeGieU
Date: 20.02.2019
*/

import org.springframework.web.multipart.MultipartFile;

public interface ImageService
{
    void SaveImageFile(Long bookId, MultipartFile file);
}
