package com.technicaltest.test.auth.services;

import com.technicaltest.test.auth.models.entities.User;
import com.technicaltest.test.auth.models.entities.UserPrincipal;
import com.technicaltest.test.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findbyUsername(username).orElseThrow(()-> new UsernameNotFoundException("No se ha encontrado usuario"));
        return UserPrincipal.build(user);
    }
}
