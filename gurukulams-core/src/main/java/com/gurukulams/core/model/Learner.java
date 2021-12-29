package com.gurukulams.core.model;

import java.time.LocalDateTime;

public record Learner(Long id, String name, String email,
                      String imageUrl, String password,
                      String provider, String providerId,
                      String displayName, LocalDateTime createdAt,
                      LocalDateTime modifiedAt) {
}
