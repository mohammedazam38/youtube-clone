package com.azam.youtubeclone.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CommentDto {

    private String commentText;
    private String authorId;
}
