package com.hsingh.urlshortener.service;

import org.springframework.stereotype.Service;

public interface UrlService {
    String shortenUrl(String originalUrl);
    String getOriginalUrl(String shortCode);
}
