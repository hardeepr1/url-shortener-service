package com.hsingh.urlshortener.controller;

import com.hsingh.urlshortener.dto.UrlRequestDto;
import com.hsingh.urlshortener.dto.UrlResponseDto;
import com.hsingh.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class UrlController {

    UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService){
        this.urlService = urlService;
    }

    @PostMapping("/data/shorten")
    public ResponseEntity<UrlResponseDto> shortenUrl(@RequestBody UrlRequestDto urlRequestDto) {
        String shortenedUrl = urlService.shortenUrl(urlRequestDto.getOriginalUrl());
        UrlResponseDto response = new UrlResponseDto(shortenedUrl, urlRequestDto.getOriginalUrl());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/shortUrl/{shortCode}")
    public ResponseEntity<String> redirect(@PathVariable String shortCode) {
        String originalUrl = urlService.getOriginalUrl(shortCode);
        //To redirect set appropriate header and 302 status
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(originalUrl));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
