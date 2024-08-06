package com.manageblogpost.blog_post_manage.repositories;

import com.manageblogpost.blog_post_manage.models.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
}
