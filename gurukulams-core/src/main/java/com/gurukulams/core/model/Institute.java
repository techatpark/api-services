package com.gurukulams.core.model;

import java.time.LocalDate;

public record Institute(Long id, String title, String description,
                        LocalDate created_at, String created_by) {


}
