package com.hsingh.urlshortener.service;

import com.hsingh.urlshortener.model.UrlMapping;
import com.hsingh.urlshortener.repository.UrlMappingRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(properties = {
        "shortener.base-url=https://override.test"
})
public class UrlShortenerServiceTest {

    @Mock
    UrlMappingRepository urlMappingRepository;

    @InjectMocks
    UrlShortenerShortenerServiceImpl urlService;

    @Test
    void testShortenUrl_success() {
        //Arrange
        String longUrl = "https://example.com";
        UrlMapping mockMapping = new UrlMapping("abc123", longUrl);
        when(urlMappingRepository.save(any())).thenReturn(mockMapping);

        //Act
        String shortenedUrl = urlService.shortenUrl(longUrl);

        //Assert
        assertNotNull(shortenedUrl);
        assertEquals("https://override.test/abc123", shortenedUrl);
    }

    @Test
    void testGetOriginalUrl_success() {
        //Arrange
        String shortcode = "abc123";
        UrlMapping mapping = new UrlMapping(shortcode, "https://example.com");
        when(urlMappingRepository.findByShortCode(shortcode)).thenReturn(Optional.of(mapping));

        //Act
        String longUrl = urlService.getOriginalUrl(shortcode);

        //Assert
        assertEquals("https://example.com", longUrl);
    }

}
