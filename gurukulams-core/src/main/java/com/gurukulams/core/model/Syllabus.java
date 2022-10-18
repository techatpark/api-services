package com.gurukulams.core.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record Syllabus(Long id, String title, String description,
                       @Schema(accessMode = Schema.AccessMode.READ_ONLY)
                       LocalDateTime created_at,
                       @Schema(accessMode = Schema.AccessMode.READ_ONLY)
                       String created_by,
                       @Schema(accessMode = Schema.AccessMode.READ_ONLY)
                       LocalDateTime modified_at,
                       @Schema(accessMode = Schema.AccessMode.READ_ONLY)
                       String modified_by) {
    }

