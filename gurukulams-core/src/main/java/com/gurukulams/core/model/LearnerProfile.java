package com.gurukulams.core.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record LearnerProfile(@Schema(accessMode = Schema.AccessMode.WRITE_ONLY)
                             String id,
                             UUID learnerId,
                             @Schema(accessMode = Schema.AccessMode.READ_ONLY)
                             String firstName,
                             @Schema(accessMode = Schema.AccessMode.READ_ONLY)
                             String lastName) {
}
