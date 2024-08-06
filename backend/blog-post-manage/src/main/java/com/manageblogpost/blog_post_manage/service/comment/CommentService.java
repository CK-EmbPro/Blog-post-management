package com.manageblogpost.blog_post_manage.service.comment;

import com.manageblogpost.blog_post_manage.excpetions.ResourceNotFoundException;
import com.manageblogpost.blog_post_manage.models.comment.dto.CommentDto;
import com.manageblogpost.blog_post_manage.models.comment.dto.CreateCommentDto;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    CommentDto commentOnPost(CreateCommentDto createCommentDto);
    CommentDto getCommentOnPost(UUID commentId) ;
    List<CommentDto> getCommentsOnPost(UUID postId);
    CommentDto updateCommentOnPost(UUID commentId, CommentDto commentDto);
    boolean deleteCommentOnPost(UUID commentId);
    boolean deleteCommentsOnPost(UUID postId);
    boolean deleteAllComments();
}
