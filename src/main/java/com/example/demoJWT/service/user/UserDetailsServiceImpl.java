package com.example.demoJWT.service.user;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoJWT.entity.User;
import com.example.demoJWT.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.fetchBySreenname(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		/*
		 * Set<Role> roles = user.getRoles(); for (Role role : roles) {
		 * grantedAuthorities.add(new SimpleGrantedAuthority(role.getName())); }
		 */

        return new org.springframework.security.core.userdetails.User(
                user.getSreenname(), user.getPassword_(), grantedAuthorities);
    }

}