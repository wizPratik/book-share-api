package com.book_share.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public final class HomeController {

    @GetMapping("/")
    public String index() {
        return "Hello World!";
    }

}
