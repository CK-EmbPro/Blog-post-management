package com.manageblogpost.blog_post_manage.service.post.impl;

import com.manageblogpost.blog_post_manage.excpetions.ResourceNotFoundException;
import com.manageblogpost.blog_post_manage.models.post.Post;
import com.manageblogpost.blog_post_manage.models.post.dto.CreatePostDto;
import com.manageblogpost.blog_post_manage.models.post.dto.PostDto;
import com.manageblogpost.blog_post_manage.models.utils.PostMapper;
import com.manageblogpost.blog_post_manage.repositories.PostRepository;
import com.manageblogpost.blog_post_manage.service.comment.impl.CommentServiceImpl;
import com.manageblogpost.blog_post_manage.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CommentServiceImpl commentService;

    @Override
    public PostDto postOnBlog(CreatePostDto createPostDto) {
        Post savedPost = null;
        try {
            Post toBeSaved = PostMapper.createPostDtoToPost(createPostDto);
            savedPost = postRepository.save(toBeSaved);
        } catch (Exception e) {
            log.error("Something happened: " + e.getMessage());
        }
        assert savedPost != null;
        return PostMapper.postToPostDto(savedPost);

    }

    @Override
    public PostDto getPostOnBlog(UUID postId) {
        Optional<Post> post = Optional.empty();
        try {
            post = postRepository.findById(postId);
            if (post.isEmpty()) throw new ResourceNotFoundException("Post " + post + " not found");
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something happened: " + e.getMessage());
        }
        return PostMapper.postToPostDto(post.get());

    }

    @Override
    public List<PostDto> getAllPostsOnBlog() {
        List<Post> allPosts = null;
        try {
            allPosts = postRepository.findAll();

        } catch (Exception e) {
            log.error("Something happened: " + e.getMessage());
        }
        assert allPosts != null;
        return allPosts
                .stream()
                .map(PostMapper::postToPostDto)
                .toList();

    }

    @Override
    public PostDto updatePost(UUID postId, PostDto postDto) {
        Post updatedPost = null;
        try {
            Optional<Post> post = postRepository.findById(postId);
            if (post.isEmpty()) throw new ResourceNotFoundException("Post " + postId + " not found");

            post.get().setTitle(postDto.getTitle());
            post.get().setAuthor(postDto.getAuthor());
            post.get().setContent(postDto.getContent());

            updatedPost = postRepository.save(post.get());
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something happened: {}", e.getMessage());
            log.error("stack trace: ", e);
        }
        assert updatedPost != null;
        return PostMapper.postToPostDto(updatedPost);

    }

    @Override
    public boolean deletePost(UUID postId) {
        boolean commentsOnPostDeleted = false;
        try {
            Optional<Post> comment = postRepository.findById(postId);
            if (comment.isEmpty()) throw new ResourceNotFoundException("Post " + postId + " not found");
            postRepository.delete(comment.get());
            commentsOnPostDeleted = commentService.deleteCommentsOnPost(postId);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("Something happened: " + e.getMessage());
        }
        return !postRepository.existsById(postId) && commentsOnPostDeleted;


    }

    @Override
    public boolean deleteAllPosts() {
        boolean allCommentDeleted = false;
        List<Post> remainingPosts = null;
        try {
            postRepository.deleteAll();
            allCommentDeleted = commentService.deleteAllComments();
            remainingPosts = postRepository.findAll();
        } catch (Exception e) {
            log.error("Something happened: " + e.getMessage());
        }

        assert remainingPosts != null;
        return remainingPosts.isEmpty() && allCommentDeleted;


    }
}
