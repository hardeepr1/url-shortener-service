package com.hsingh.urlshortener.service;

import org.springframework.stereotype.Service;

@Service
public class UrlServiceImpl implements UrlService {
    @Override
    public String shortenUrl(String originalUrl) {
        return "";
    }

    @Override
    public String getOriginalUrl(String shortCode) {
        return "";
    }
}
