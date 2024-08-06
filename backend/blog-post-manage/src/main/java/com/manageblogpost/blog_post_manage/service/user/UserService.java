package com.manageblogpost.blog_post_manage.service.user;

import com.manageblogpost.blog_post_manage.excpetions.BadRequestException;
import com.manageblogpost.blog_post_manage.models.user.dto.LogInDetailsDTO;
import com.manageblogpost.blog_post_manage.models.user.dto.LoginDTO;
import com.manageblogpost.blog_post_manage.models.user.dto.UserRegistrationDTO;

public interface UserService {
    LogInDetailsDTO registerUser(UserRegistrationDTO registrationDTO) throws BadRequestException;
    LogInDetailsDTO login(LoginDTO loginDTO);
    LogInDetailsDTO getCurrentUser();
}
