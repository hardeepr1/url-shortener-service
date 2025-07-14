package com.hsingh.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class UrlShortenerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerServiceApplication.class, args);
	}

}
