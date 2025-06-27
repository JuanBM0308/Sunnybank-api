package com.juanba.sunnybank.infrastructure.security.config;

import com.juanba.sunnybank.infrastructure.persistance.user.repository.SpringDataUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationProvider implements UserDetailsService {

    private final SpringDataUserRepository springDataUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return springDataUserRepository.findByEmail(username);
    }
}
