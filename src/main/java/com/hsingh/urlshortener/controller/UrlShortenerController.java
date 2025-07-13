package com.hsingh.urlshortener.controller;

import com.hsingh.urlshortener.dto.UrlRequestDto;
import com.hsingh.urlshortener.dto.UrlResponseDto;
import com.hsingh.urlshortener.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class UrlShortenerController {

    UrlShortenerService urlShortenerService;

    @Autowired
    public UrlShortenerController(UrlShortenerService urlShortenerService){
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping("/data/shorten")
    public ResponseEntity<UrlResponseDto> shortenUrl(@RequestBody UrlRequestDto urlRequestDto) {
        String shortenedUrl = urlShortenerService.shortenUrl(urlRequestDto.getOriginalUrl(), urlRequestDto.getCustomShortCode());
        UrlResponseDto response = new UrlResponseDto(shortenedUrl, urlRequestDto.getOriginalUrl());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/shortUrl/{shortCode}")
    public ResponseEntity<String> redirect(@PathVariable String shortCode) {
        String originalUrl = urlShortenerService.getOriginalUrl(shortCode);
        //To redirect set appropriate header and 302 status
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(originalUrl));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
