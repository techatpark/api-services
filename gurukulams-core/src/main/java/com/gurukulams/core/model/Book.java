package com.gurukulams.core.model;

import java.time.LocalDateTime;

public record Book(Long id, String title, String description,
                   LocalDateTime created_at, String created_by,
                   LocalDateTime modified_at, String modified_by) {
    }

