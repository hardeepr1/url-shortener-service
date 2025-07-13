package com.hsingh.urlshortener.dto;

public class UrlRequestDto {
    private String originalUrl;
    private String customShortCode; //Optional

    public UrlRequestDto() {}

    public UrlRequestDto(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }


    public String getCustomShortCode() {
        return customShortCode;
    }

    public void setCustomShortCode(String customShortCode) {
        this.customShortCode = customShortCode;
    }
}
