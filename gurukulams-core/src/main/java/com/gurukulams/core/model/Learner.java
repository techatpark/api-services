package com.gurukulams.core.model;

import java.time.LocalDateTime;

public record Learner(Long id, String title, String description,
                      LocalDateTime createdAt, LocalDateTime modifiedAt) {
}
