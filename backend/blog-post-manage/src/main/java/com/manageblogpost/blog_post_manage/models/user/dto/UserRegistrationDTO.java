package com.manageblogpost.blog_post_manage.models.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegistrationDTO {
    private String firstName;
    private String LastName;
    private String email;
    private String phoneNumber;
    private String password;
}
