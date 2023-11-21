package com.company.secondhand.advertisement.dto;

import java.math.BigDecimal;
import java.util.Set;

public record CreateAdvertisementRequest(
        String title,
        String description,
        BigDecimal price,
        Long userId,
        Set<String> hashtag
) {
}
