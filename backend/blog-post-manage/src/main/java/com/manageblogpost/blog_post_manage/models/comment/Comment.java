package com.manageblogpost.blog_post_manage.models.comment;

import com.manageblogpost.blog_post_manage.models.utils.TimeStampAudit;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.CookieValue;

import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Comment")
public class Comment extends TimeStampAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "comment_id")
    private UUID commentId;

    @Column(name = "post_id", nullable = false)
    private UUID postId;

    @Column(name = "content")
    private String content;

    @Column(name = "author")
    private String author;

}
