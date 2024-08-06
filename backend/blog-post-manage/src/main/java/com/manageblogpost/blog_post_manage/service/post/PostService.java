package com.manageblogpost.blog_post_manage.service.post;
import com.manageblogpost.blog_post_manage.models.post.dto.CreatePostDto;
import com.manageblogpost.blog_post_manage.models.post.dto.PostDto;
import java.util.List;
import java.util.UUID;

public interface PostService {
    PostDto postOnBlog(CreatePostDto createPostDto);
    PostDto getPostOnBlog(UUID postId);
    List<PostDto> getAllPostsOnBlog();
    PostDto updatePost(UUID postId, PostDto postDto);
    boolean deletePost(UUID postId);
    boolean deleteAllPosts();
}
