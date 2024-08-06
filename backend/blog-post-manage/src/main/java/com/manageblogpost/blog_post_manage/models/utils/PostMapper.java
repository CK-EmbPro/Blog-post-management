package com.manageblogpost.blog_post_manage.models.utils;

import com.manageblogpost.blog_post_manage.models.comment.Comment;
import com.manageblogpost.blog_post_manage.models.comment.dto.CommentDto;
import com.manageblogpost.blog_post_manage.models.comment.dto.CreateCommentDto;
import com.manageblogpost.blog_post_manage.models.post.Post;
import com.manageblogpost.blog_post_manage.models.post.dto.CreatePostDto;
import com.manageblogpost.blog_post_manage.models.post.dto.PostDto;

public class PostMapper {
    public static Post createPostDtoToPost(CreatePostDto createPostDto){
        return Post.builder()
                .title(createPostDto.getTitle())
                .content(createPostDto.getContent())
                .author(createPostDto.getAuthor())
                .build();
    }

    public static Post postDtoToPost(PostDto postDto){
        return Post.builder()
                .postId(postDto.getPostId())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .author(postDto.getAuthor())
                .build();
    }

    public static PostDto postToPostDto(Post post){
        return PostDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .build();
    }
}
