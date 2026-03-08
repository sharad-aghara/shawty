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
// Design pattern: Retry pattern

@Service
@RequiredArgsConstructor
public class UrlService {

    private static final int MAX_RETRIES = 3;
    private final UrlRepository repository;

    public CreateUrlResponse createShortUrl(CreateUrlRequest request) {
        String shortCode = generateUniqueShortCode();

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

    private String generateUniqueShortCode() {
        for (int i = 0; i < MAX_RETRIES; i++) {
            String shortCode = ShortcodeGenerator.generate();

            if (!repository.existsByShortCode(shortCode)) return shortCode;

        }

        throw new RuntimeException("Failed to generate shortcode.");
    }

    public String getOriginalUrl(String shortCode) {
        Url url = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("Url not found!"));

        return url.getOriginalUrl();
    }

}
