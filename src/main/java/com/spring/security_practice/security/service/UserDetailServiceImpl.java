package com.spring.security_practice.security.service;

import com.spring.security_practice.domain.Role;
import com.spring.security_practice.domain.User;
import com.spring.security_practice.exception.StudentNotFoundException;
import com.spring.security_practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    //This method is used to load the user by username
    //This method is called by the Spring Security to load the user details when the user logs in
    //This method returns the UserDetails object which contains the information about the user
    //This method throws UsernameNotFoundException if the user is not found
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username).orElseThrow(() -> new StudentNotFoundException("User Not Found"));

        /*if (user  != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(buildGrantedAuthorities(user.getRoles()))
                    .build();
        }*/

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), buildGrantedAuthorities(user.getRoles()));
        }
        else {
            throw new UsernameNotFoundException("User not found" + username);
        }

    }

    //This helper method is used to convert the Role to a list of GrantedAuthority
    private static List<SimpleGrantedAuthority> buildGrantedAuthorities(final Set<Role> roles) {
        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return authorities;

    }
}
