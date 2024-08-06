package com.manageblogpost.blog_post_manage.models.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogInDetailsDTO {
    private String token;
    private String email;
    private String firstName;
    private String lastName;
}
