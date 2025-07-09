package com.hsingh.urlshortener.dto;

public class UrlRequestDto {
    private String longUrl;

    public UrlRequestDto() {}

    public UrlRequestDto(String originalUrl) {
        this.longUrl = originalUrl;
    }

    public String getOriginalUrl() {
        return longUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.longUrl = originalUrl;
    }
}
