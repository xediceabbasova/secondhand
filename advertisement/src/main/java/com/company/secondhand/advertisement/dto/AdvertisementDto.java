package com.company.secondhand.advertisement.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record AdvertisementDto(
        String id,
        String title,
        String description,
        BigDecimal price,
        LocalDateTime creationDate,
        LocalDateTime lastModifiedDate,
        Long userId,
        Set<String> hashtags
) {
}
