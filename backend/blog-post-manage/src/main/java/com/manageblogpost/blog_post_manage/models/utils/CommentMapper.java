package com.manageblogpost.blog_post_manage.models.utils;

import com.manageblogpost.blog_post_manage.models.comment.Comment;
import com.manageblogpost.blog_post_manage.models.comment.dto.CommentDto;
import com.manageblogpost.blog_post_manage.models.comment.dto.CreateCommentDto;

public class CommentMapper {
    public static Comment createCommentDtoToComment(CreateCommentDto createCommentDto){
        return Comment.builder()
                .postId(createCommentDto.getPostId())
                .content(createCommentDto.getContent())
                .author(createCommentDto.getAuthor())
                .build();
    }

    public static Comment commentDtoToComment(CommentDto commentDto){
        return Comment.builder()
                .commentId(commentDto.getCommentId())
                .postId(commentDto.getPostId())
                .content(commentDto.getContent())
                .author(commentDto.getAuthor())
                .build();
    }

    public static CommentDto commentToCommentDto(Comment comment){
        return CommentDto.builder()
                .commentId(comment.getCommentId())
                .postId(comment.getPostId())
                .content(comment.getContent())
                .author(comment.getAuthor())
                .build();
    }


}
