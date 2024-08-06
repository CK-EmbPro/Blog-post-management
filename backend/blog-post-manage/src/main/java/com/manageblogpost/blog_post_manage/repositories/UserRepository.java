package com.manageblogpost.blog_post_manage.repositories;

import com.manageblogpost.blog_post_manage.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
    Optional<User> findFirstByEmail(String email);


}
