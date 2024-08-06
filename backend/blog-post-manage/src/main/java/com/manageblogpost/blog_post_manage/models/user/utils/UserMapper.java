package com.manageblogpost.blog_post_manage.models.user.utils;

import com.manageblogpost.blog_post_manage.models.user.User;
import com.manageblogpost.blog_post_manage.models.user.dto.UserRegistrationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.parsing.PassThroughSourceExtractor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserMapper {
    private static PasswordEncoder passwordEncoder = null;
    public static User registerDtoToUser(UserRegistrationDTO registerDto, PasswordEncoder passwordEncoder1){
        passwordEncoder = passwordEncoder1;
        return User.builder()
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .phoneNumber(registerDto.getPhoneNumber())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .build();
    }
}
