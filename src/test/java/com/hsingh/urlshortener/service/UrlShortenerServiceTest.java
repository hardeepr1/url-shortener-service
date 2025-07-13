package com.hsingh.urlshortener.service;

import com.hsingh.urlshortener.model.UrlMapping;
import com.hsingh.urlshortener.repository.UrlMappingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UrlShortenerServiceTest {
    @Value("${shortener.base-url}")
    private String baseUrl;

    @Mock
    UrlMappingRepository urlMappingRepository;

    UrlShortenerServiceImpl urlService;

    @BeforeEach
    void setUp() {
        urlService = new UrlShortenerServiceImpl("https://test.baseurl", urlMappingRepository);
    }

    @Test
    void testShortenUrl_success() {
        //Arrange
        String longUrl = "https://longurl.example.com";
        UrlMapping mockMapping = new UrlMapping("abc123", longUrl);
        mockMapping.setId(123L);
        when(urlMappingRepository.save(any(UrlMapping.class)))
                .thenAnswer(invocation -> {
                    UrlMapping mapping = invocation.getArgument(0);
                    mapping.setId(123L); // simulate DB assigning ID
                    return mapping;
                });

        //Act
        String shortenedUrl = urlService.shortenUrl(longUrl);

        //Assert
        assertNotNull(shortenedUrl);
        assertEquals("https://test.baseurl/1Z", shortenedUrl);
    }

    @Test
    void testGetOriginalUrl_success() {
        //Arrange
        String shortCode = "1Z4fer";
        UrlMapping mapping = new UrlMapping(shortCode, "https://example.com");
        when(urlMappingRepository.findByShortCode(shortCode)).thenReturn(Optional.of(mapping));

        //Act
        String longUrl = urlService.getOriginalUrl(shortCode);

        //Assert
        assertEquals("https://example.com", longUrl);
    }

}
