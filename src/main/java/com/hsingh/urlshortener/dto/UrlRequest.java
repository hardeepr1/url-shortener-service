package com.hsingh.urlshortener.dto;

public class UrlRequest {
    private String longUrl;

    public UrlRequest() {}

    public UrlRequest(String originalUrl) {
        this.longUrl = originalUrl;
    }

    public String getOriginalUrl() {
        return longUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.longUrl = originalUrl;
    }
}
