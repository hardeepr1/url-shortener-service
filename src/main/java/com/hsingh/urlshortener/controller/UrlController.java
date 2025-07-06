package com.hsingh.urlshortener.controller;

import com.hsingh.urlshortener.dto.UrlRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class UrlController {

    @PostMapping("/data/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody UrlRequest urlRequest) {
        //TODO: implementation after injecting service
        return ResponseEntity.status(HttpStatus.OK).body("mock result");
    }

    @GetMapping("/shortUrl/{shortCode}")
    public ResponseEntity<String> redirect(@PathVariable String shortCode) {
        //TODO: implementation after injecting service
        String originalUrl = "mock url";

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(originalUrl));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
