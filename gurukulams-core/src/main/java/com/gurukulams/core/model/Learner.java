package com.gurukulams.core.model;

import java.time.LocalDateTime;

public record Learner(Long id, String name, String email,
                      String displaying, LocalDateTime createdAt,
                      LocalDateTime modifiedAt) {
}
