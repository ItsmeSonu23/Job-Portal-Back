package com.jobportal.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.jobportal.service.UserService;
import com.jobportal.dto.UserDto;
import java.util.ArrayList;
import com.jobportal.exception.JobPortalException;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user;
        try {
            user = userService.getUserByEmail(username);
        } catch (JobPortalException e) {
            throw new UsernameNotFoundException("User not found with username: " + username, e);
        }
        return new CustomUserDetails(user.getId(), username, user.getName(), user.getPassword(), user.getProfileId(), user.getAccountType(), new ArrayList<>());
    }

    
}