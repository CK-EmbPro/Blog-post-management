package com.manageblogpost.blog_post_manage.service.comment.impl;

import com.manageblogpost.blog_post_manage.excpetions.ResourceNotFoundException;
import com.manageblogpost.blog_post_manage.models.comment.Comment;
import com.manageblogpost.blog_post_manage.models.comment.dto.CommentDto;
import com.manageblogpost.blog_post_manage.models.comment.dto.CreateCommentDto;
import com.manageblogpost.blog_post_manage.models.utils.CommentMapper;
import com.manageblogpost.blog_post_manage.repositories.CommentRepository;
import com.manageblogpost.blog_post_manage.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public CommentDto commentOnPost(CreateCommentDto createCommentDto) {
        try {
            Comment toBeSaved = CommentMapper.createCommentDtoToComment(createCommentDto);
            Comment savedComment = commentRepository.save(toBeSaved);
            return CommentMapper.commentToCommentDto(savedComment);
        } catch (Exception e) {
            log.error("Something happened: {}", e.getMessage());
            return null;
        }

    }

    @Override
    public CommentDto getCommentOnPost(UUID commentId) {
        Optional<Comment> comment = null;
        try {
            comment = commentRepository.findById(commentId);
            if (comment.isEmpty()) throw new ResourceNotFoundException("Comment " + commentId + " not found");
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something happened: {}", e.getMessage());
        }
        return CommentMapper.commentToCommentDto(comment.get());
    }

    @Override
    public List<CommentDto> getCommentsOnPost(UUID postId) {
        List<Comment> commentsOnPost = null;
        try {
            commentsOnPost = commentRepository.findCommentsByPostId(postId);
        } catch (Exception e) {
            log.error("Something happened: {}", e.getMessage());
        }

        return commentsOnPost
                .stream()
                .map(CommentMapper::commentToCommentDto)
                .toList();

    }

    @Override
    public CommentDto updateCommentOnPost(UUID commentId, CommentDto commentDto) {
        Comment updatedComment = null;
        try {
            Optional<Comment> comment = commentRepository.findById(commentId);
            if (comment.isEmpty()) throw new ResourceNotFoundException("Comment " + commentId + " not found");

            comment.get().setAuthor(commentDto.getAuthor());
            comment.get().setContent(commentDto.getContent());

            updatedComment = commentRepository.save(comment.get());
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something happened: {}", e.getMessage());
        }
        return CommentMapper.commentToCommentDto(updatedComment);

    }

    @Override
    public boolean deleteCommentOnPost(UUID commentId) {
        try {
            Optional<Comment> comment = commentRepository.findById(commentId);
            if (comment.isEmpty()) throw new ResourceNotFoundException("Comment " + commentId + " not found");
            commentRepository.delete(comment.get());
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something happened: {}", e.getMessage());
        }

        return !commentRepository.existsById(commentId);


    }

    @Override
    public boolean deleteCommentsOnPost(UUID postId)  {
        try {
            List<Comment> commentsOnPost = commentRepository.findCommentsByPostId(postId);
            if (commentsOnPost.isEmpty())
                throw new ResourceNotFoundException("No comments of this post" + postId + " found");
            commentRepository.deleteAll(commentsOnPost);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something happened: {}", e.getMessage());
        }
        return !commentRepository.existsById(postId);

    }

    @Override
    public boolean deleteAllComments() {
        boolean allCommentsDeleted = false;
        try {
            commentRepository.deleteAll();
            allCommentsDeleted= commentRepository.findAll().isEmpty();
        } catch (Exception e) {
            log.error("Something happened: {}", e.getMessage());
        }
        return allCommentsDeleted;
    }
}
