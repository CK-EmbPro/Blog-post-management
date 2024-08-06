package com.manageblogpost.blog_post_manage.controller.post;
import com.manageblogpost.blog_post_manage.controller.utils.ApiResponse;
import com.manageblogpost.blog_post_manage.models.post.dto.CreatePostDto;
import com.manageblogpost.blog_post_manage.models.post.dto.PostDto;
import com.manageblogpost.blog_post_manage.service.post.impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostServiceImpl postService;

    @PostMapping
    public ResponseEntity<ApiResponse<PostDto>> postOnBlog(@RequestBody CreatePostDto createPostDto) {
        PostDto post = postService.postOnBlog(createPostDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(post, "posted successfully " + post.getPostId(), "SUCCESS", HttpStatus.CREATED));

    }

    @GetMapping("{post_id}")
    public ResponseEntity<ApiResponse<PostDto>> getPostOnBlog(@PathVariable UUID post_id) {
        PostDto postOnBlog = postService.getPostOnBlog(post_id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(postOnBlog, "retrieved successfully post "+post_id, "SUCCESS", HttpStatus.OK));

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostDto>>> getAllPostsOnBlog() {
        List<PostDto> allPostsOnBlog = postService.getAllPostsOnBlog();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(allPostsOnBlog, "All posts on blog ", "SUCCESS", HttpStatus.OK));

    }

    @PutMapping("{post_id}")
    public ResponseEntity<ApiResponse<PostDto>> updatePost(@PathVariable UUID post_id, @RequestBody PostDto postDto) {
        PostDto updatedPost = postService.updatePost(post_id, postDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(updatedPost, "Post "+post_id+" updated successfully", "SUCCESS", HttpStatus.OK));

    }

    @DeleteMapping("{post_id}")
    public ResponseEntity<ApiResponse<Boolean>> deletePost(@PathVariable UUID post_id) {
        boolean isDeleted = postService.deletePost(post_id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(isDeleted, "Post " + post_id+" deleted successfully", "SUCCESS", HttpStatus.OK));

    }


    @DeleteMapping
    public ResponseEntity<ApiResponse<Boolean>> deleteAllComments() {
        boolean isDeleted = postService.deleteAllPosts();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(isDeleted, "All post deleted successfully", "SUCCESS", HttpStatus.OK));

    }
}
