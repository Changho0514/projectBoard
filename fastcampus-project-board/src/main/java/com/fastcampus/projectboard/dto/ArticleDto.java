package com.fastcampus.projectboard.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ArticleDto(
        LocalDateTime createAt,
        String createdBy,
        String title,
        String content,
        String hashtag
) implements Serializable {

    public static ArticleDto of(LocalDateTime createAt, String createdBy, String title, String content, String hashtag) {
        return new ArticleDto(createAt, createdBy, title, content, hashtag);
    }
}
