package com.manageblogpost.blog_post_manage.controller.comment;

import com.manageblogpost.blog_post_manage.controller.utils.ApiResponse;
import com.manageblogpost.blog_post_manage.models.comment.dto.CommentDto;
import com.manageblogpost.blog_post_manage.models.comment.dto.CreateCommentDto;
import com.manageblogpost.blog_post_manage.service.comment.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentServiceImpl commentService;

    @PostMapping
    public ResponseEntity<ApiResponse<CommentDto>> commentOnPost(@RequestBody CreateCommentDto createCommentDto) {
        CommentDto comment = commentService.commentOnPost(createCommentDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(comment, "Commented successfully on post " + comment.getPostId(), "SUCCESS", HttpStatus.CREATED));

    }

    @GetMapping("{comment_id}")
    public ResponseEntity<ApiResponse<CommentDto>> getCommentOnPost(@PathVariable UUID comment_id) {
        CommentDto commentOnPost = commentService.getCommentOnPost(comment_id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(commentOnPost, "Comment with id " + comment_id, "SUCCESS", HttpStatus.OK));

    }

    @GetMapping("{post_id}")
    public ResponseEntity<ApiResponse<List<CommentDto>>> getCommentsOnPost(@PathVariable UUID post_id) {
        List<CommentDto> commentsOnPost = commentService.getCommentsOnPost(post_id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(commentsOnPost, "Comments on post " + post_id, "SUCCESS", HttpStatus.OK));

    }

    @PutMapping("{comment_id}")
    public ResponseEntity<ApiResponse<CommentDto>> updateCommentOnPost(@PathVariable UUID comment_id, @RequestBody CommentDto commentDto) {
        CommentDto updatedComment = commentService.updateCommentOnPost(comment_id, commentDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(updatedComment, "Comment " + comment_id+" updated successfully", "SUCCESS", HttpStatus.OK));

    }

    @DeleteMapping("{comment_id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteCommentOnPost(@PathVariable UUID comment_id) {
        boolean isDeleted = commentService.deleteCommentOnPost(comment_id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(isDeleted, "Comment " + comment_id+" deleted successfully", "SUCCESS", HttpStatus.OK));

    }

    @DeleteMapping("{post_id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteCommentsOnPost(@PathVariable UUID post_id) {
        boolean isDeleted = commentService.deleteCommentsOnPost(post_id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(isDeleted, "All comments of post " + post_id+" deleted successfully", "SUCCESS", HttpStatus.OK));

    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Boolean>> deleteAllComments() {
        boolean isDeleted = commentService.deleteAllComments();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(isDeleted, "All comments deleted successfully", "SUCCESS", HttpStatus.OK));

    }
}
