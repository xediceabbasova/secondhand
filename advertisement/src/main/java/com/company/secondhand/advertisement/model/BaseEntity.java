package com.company.secondhand.advertisement.model;

import java.time.LocalDateTime;

public record BaseEntity(LocalDateTime createdDate, LocalDateTime updatedDate) {
}

