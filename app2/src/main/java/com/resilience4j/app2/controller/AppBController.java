package com.resilience4j.app2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/b")
public class AppBController {

    @GetMapping
    public ResponseEntity serviceB() {
        return new ResponseEntity<>("Hello World!", HttpStatus.BAD_REQUEST);
    }

}
