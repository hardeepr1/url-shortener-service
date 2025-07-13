package com.hsingh.urlshortener.service;

import java.util.Optional;

public interface UrlShortenerService {
    String shortenUrl(String originalUrl, String customShortCode);
    String getOriginalUrl(String shortCode);
}
