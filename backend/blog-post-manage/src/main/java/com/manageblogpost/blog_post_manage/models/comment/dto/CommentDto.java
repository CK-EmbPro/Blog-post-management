package com.manageblogpost.blog_post_manage.models.comment.dto;

import lombok.*;

import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    @NonNull
    private UUID commentId;
    @NonNull
    private UUID postId;
    @NonNull
    private String content;
    @NonNull
    private String author;
}
