package com.manageblogpost.blog_post_manage.repositories;

import com.manageblogpost.blog_post_manage.models.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findCommentsByPostId(UUID postId);
}
