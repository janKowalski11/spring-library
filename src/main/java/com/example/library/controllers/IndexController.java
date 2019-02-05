package com.example.library.controllers;
/*
Author: BeGieU
Date: 04.02.2019
*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController
{
    //todo refactor to use one navigation bar in all html pages
    @GetMapping({"", "/", "index", "templates/index.html"})
    public String index()
    {
        return "index";
    }
}
