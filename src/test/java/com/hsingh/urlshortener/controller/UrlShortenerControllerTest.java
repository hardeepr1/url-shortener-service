package com.hsingh.urlshortener.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsingh.urlshortener.dto.UrlRequestDto;
import com.hsingh.urlshortener.service.UrlShortenerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UrlShortenerController.class)
public class UrlShortenerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UrlShortenerService urlShortenerService;

    @Test
    void testShortenUrlApi() throws Exception {
        String longUrl = "https://longurl.com";
        UrlRequestDto request = new UrlRequestDto(longUrl);

        when(urlShortenerService.shortenUrl(longUrl)).thenReturn("shortcode");

        mockMvc.perform(post("/api/v1/data/shorten")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shortCode").value("shortcode"));
    }

    @Test
    void testResolveShortCodeApi() throws Exception {
        when(urlShortenerService.getOriginalUrl("xy43d4")).thenReturn("https://longurl.com");

        mockMvc.perform(get("/shortUrl/xy43d4"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("https://longurl.com"));
    }
}
