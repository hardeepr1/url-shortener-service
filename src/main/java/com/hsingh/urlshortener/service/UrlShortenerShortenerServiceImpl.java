package com.hsingh.urlshortener.service;

import com.hsingh.urlshortener.model.UrlMapping;
import com.hsingh.urlshortener.repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerShortenerServiceImpl implements UrlShortenerService {

    @Value("${shortener.base-url}")
    private String baseUrl;

    @Autowired
    UrlMappingRepository urlMappingRepository;

    @Override
    public String shortenUrl(String originalUrl) {
        String shortCode = generateShortCode();

        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortCode(shortCode);

        urlMappingRepository.save(urlMapping);

        return baseUrl + "/" + shortCode;
    }

    @Override
    public String getOriginalUrl(String shortCode) {
        UrlMapping urlMapping = urlMappingRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("Short code not found"));
        return urlMapping.getOriginalUrl();
    }

    //TODO: write the algorithm to generate hashcode
    private String generateShortCode() {
        return "ABCDEF";
    }
}
