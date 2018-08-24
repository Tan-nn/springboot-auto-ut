package com.example.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class IndexController {

    @RequestMapping("/")
    public String index() {
        System.out.println("index controller");
        return "Welcome to MoonPark!";
    }

}