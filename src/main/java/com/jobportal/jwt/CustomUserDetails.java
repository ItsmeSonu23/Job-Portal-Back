package com.jobportal.jwt;

import org.springframework.security.core.userdetails.UserDetails;

import com.jobportal.dto.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {

    private Long id;
    private String username;
    private String name;
    private String password;
    private Long profileId;
    private AccountType accountType;
    private Collection<? extends GrantedAuthority> authorities;

    
}
