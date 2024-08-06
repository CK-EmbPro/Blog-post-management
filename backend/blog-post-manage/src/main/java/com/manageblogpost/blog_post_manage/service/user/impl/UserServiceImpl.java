package com.manageblogpost.blog_post_manage.service.user.impl;

import com.manageblogpost.blog_post_manage.excpetions.BadRequestException;
import com.manageblogpost.blog_post_manage.excpetions.ResourceNotFoundException;
import com.manageblogpost.blog_post_manage.models.user.User;
import com.manageblogpost.blog_post_manage.models.user.dto.LogInDetailsDTO;
import com.manageblogpost.blog_post_manage.models.user.dto.LoginDTO;
import com.manageblogpost.blog_post_manage.models.user.dto.UserRegistrationDTO;
import com.manageblogpost.blog_post_manage.models.user.utils.UserMapper;
import com.manageblogpost.blog_post_manage.repositories.UserRepository;
import com.manageblogpost.blog_post_manage.service.security.JwtAuthentication;
import com.manageblogpost.blog_post_manage.service.user.UserService;
import com.manageblogpost.blog_post_manage.service.user.utils.BearerTokenWrapper;
import com.manageblogpost.blog_post_manage.service.user.utils.EmailPasswordValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtAuthentication jwtAuthentication;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final BearerTokenWrapper bearerTokenWrapper;

    @Override
    public LogInDetailsDTO registerUser(UserRegistrationDTO registrationDTO) throws BadRequestException {
        if (EmailPasswordValidator.isValidGmail(registrationDTO.getEmail()) && EmailPasswordValidator.isValidPassword(registrationDTO.getPassword())) {
            User toBeRegistered = UserMapper.registerDtoToUser(registrationDTO, passwordEncoder);
            User registeredUser = null;
            try {
                registeredUser = userRepository.save(toBeRegistered);
            } catch (DuplicateKeyException duplicateKeyException) {
                throw new DuplicateKeyException("User already exists");
            }
            Map<String, Object> extraClaims = new HashMap<>();
            extraClaims.put("role", registeredUser.getEmail());

            String token = jwtAuthentication.generateToken(extraClaims,registeredUser);
            return new LogInDetailsDTO(token, registeredUser.getEmail(), registeredUser.getFirstName(), registeredUser.getLastName());
        } else {
            if (!EmailPasswordValidator.isValidPassword(registrationDTO.getPassword())) {
                throw new BadRequestException("Password is not valid");
            } else if (!EmailPasswordValidator.isValidGmail(registrationDTO.getEmail())) {
                throw new BadRequestException("Email is not valid");

            }

        }

        return null;

    }

    @Override
    public LogInDetailsDTO login(LoginDTO loginDTO){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        User loggedInUser = userRepository.findByEmail(loginDTO.getEmail());
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", loggedInUser.getEmail());
        String token = jwtAuthentication.generateToken(claims, loggedInUser);
        return new LogInDetailsDTO(token, loggedInUser.getEmail(), loggedInUser.getFirstName(), loggedInUser.getLastName());

    }

    @Override
    public LogInDetailsDTO getCurrentUser() {
        return null;
    }
}
