package com.gurukulams.core.model;

import java.time.LocalDateTime;

public record Learner(Long id, String email, String password,
                      String imageUrl,
                      LocalDateTime createdAt, String createdBy,
                      LocalDateTime modifiedAt, String modifiedBy) {
}
