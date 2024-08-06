package com.manageblogpost.blog_post_manage.service.security;

import com.manageblogpost.blog_post_manage.models.user.User;
import com.manageblogpost.blog_post_manage.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findFirstByEmail(username);
        if (user.isPresent()){
            return new org.springframework.security.core.userdetails.User(user.get().getEmail(),user.get().getPassword(), List.of(new SimpleGrantedAuthority(user.get().getEmail())));
        }
        throw new UsernameNotFoundException("User not found") ;
    }
}
