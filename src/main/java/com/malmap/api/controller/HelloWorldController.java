package com.malmap.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorldController {

    @GetMapping("/hello")
    public ResponseEntity<Map<String, String>> helloWorld() {
        Map<String, String> response = new HashMap<>();
        response.put("data", "helloworld");
        return ResponseEntity.ok(response);
    }
}