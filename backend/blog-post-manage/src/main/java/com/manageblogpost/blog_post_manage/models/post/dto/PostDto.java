package com.manageblogpost.blog_post_manage.models.post.dto;

import lombok.*;

import java.util.UUID;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    @NonNull
    private UUID postId;
    @NonNull
    private String title;
    @NonNull
    private String content;
    @NonNull
    private String author;
}
