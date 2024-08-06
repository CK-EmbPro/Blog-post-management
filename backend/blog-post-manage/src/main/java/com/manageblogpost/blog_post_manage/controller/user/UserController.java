package com.manageblogpost.blog_post_manage.controller.user;

import com.manageblogpost.blog_post_manage.controller.utils.ApiResponse;
import com.manageblogpost.blog_post_manage.excpetions.BadRequestException;
import com.manageblogpost.blog_post_manage.models.user.dto.LogInDetailsDTO;
import com.manageblogpost.blog_post_manage.models.user.dto.LoginDTO;
import com.manageblogpost.blog_post_manage.models.user.dto.UserRegistrationDTO;
import com.manageblogpost.blog_post_manage.service.user.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
    private final UserServiceImpl userService;
    @PostMapping("register")
    public ResponseEntity<ApiResponse<LogInDetailsDTO>> registerUser(UserRegistrationDTO registrationDTO) throws BadRequestException {
        LogInDetailsDTO registeredUser = userService.registerUser(registrationDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(registeredUser, "Registered user successfully", "SUCCESS", HttpStatus.CREATED));
    }

    @PostMapping("login")
    public ResponseEntity<ApiResponse<LogInDetailsDTO>> login(LoginDTO loginDTO) throws BadRequestException {
        LogInDetailsDTO loggedInUser = userService.login(loginDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(loggedInUser, "Registered user successfully", "SUCCESS", HttpStatus.OK
                ));
    }
}
