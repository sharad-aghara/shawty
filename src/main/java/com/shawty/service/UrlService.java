package com.shawty.service;

import com.shawty.dto.CreateUrlRequest;
import com.shawty.dto.CreateUrlResponse;
import com.shawty.entity.Url;
import com.shawty.repository.UrlRepository;
import com.shawty.utils.ShortcodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

// Design pattern: Service layer pattern

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository repository;

    public CreateUrlResponse createShortUrl(CreateUrlRequest request) {
        String shortCode = ShortcodeGenerator.generate();

        Url url = Url.builder()
                .originalUrl(request.url())
                .shortCode(shortCode)
                .createdAt(LocalDateTime.now())
                .clickCount(0L)
                .build();

        repository.save(url);

        return new CreateUrlResponse(
                "http://localhost:8080/" + shortCode
        );
    }

    public String getOriginalUrl(String shortCode) {
        Url url = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("Url not found!"));

        return url.getOriginalUrl();
    }
}
