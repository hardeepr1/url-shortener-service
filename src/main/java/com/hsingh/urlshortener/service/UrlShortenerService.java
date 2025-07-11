package com.hsingh.urlshortener.service;

public interface UrlShortenerService {
    String shortenUrl(String originalUrl);
    String getOriginalUrl(String shortCode);
}
