package com.gurukulams.core.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record Learner(Long id, String email,
                      @Schema(accessMode = Schema.AccessMode.WRITE_ONLY)
                      String password,
                      @Schema(accessMode = Schema.AccessMode.READ_ONLY)
                      String imageUrl,
                      @Schema(accessMode = Schema.AccessMode.READ_ONLY)
                      AuthProvider provider,
                      @Schema(accessMode = Schema.AccessMode.READ_ONLY)
                      LocalDateTime createdAt,
                      @Schema(accessMode = Schema.AccessMode.READ_ONLY)
                      String createdBy,
                      @Schema(accessMode = Schema.AccessMode.READ_ONLY)
                      LocalDateTime modifiedAt,
                      @Schema(accessMode = Schema.AccessMode.READ_ONLY)
                      String modifiedBy) {
}
