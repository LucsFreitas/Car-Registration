package com.freitas.lucas.carregistration.services.impl;

import com.freitas.lucas.carregistration.domain.User;
import com.freitas.lucas.carregistration.repositories.UserRepository;
import com.freitas.lucas.carregistration.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(login));
        return new UserSS(user.getId(), user.getLogin(), user.getPassword(), user.getProfiles());
    }
}
