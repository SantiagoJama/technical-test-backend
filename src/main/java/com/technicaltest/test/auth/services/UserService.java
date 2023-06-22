package com.technicaltest.test.auth.services;

import com.technicaltest.test.auth.jwt.JwtProvider;
import com.technicaltest.test.auth.models.dto.JwtDto;
import com.technicaltest.test.auth.models.dto.Login;
import com.technicaltest.test.auth.models.dto.NewUser;
import com.technicaltest.test.auth.models.entities.Role;
import com.technicaltest.test.auth.models.entities.User;
import com.technicaltest.test.auth.models.enums.RoleName;
import com.technicaltest.test.auth.repositories.UserRepository;
import com.technicaltest.test.handler.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;


    public void save(NewUser newUser) throws BadRequestException {
        if(!newUser.getPassword().equals(newUser.getPasswordconfi())){
            throw  new BadRequestException("Passwords diferentes");
        }
        if(userRepository.existsByUsername(newUser.getUsername())){
            throw  new BadRequestException("Username ya existe");
        }
        User user = mapper(newUser);
        userRepository.save(user);
    }

    private User mapper(NewUser newUser) {
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByRolename(RoleName.ROLE_USER).get());
        if (newUser.getRoles().contains("ROLE_ADMIN"))
            roles.add(roleService.findByRolename(RoleName.ROLE_ADMIN).get());
        user.setRoles(roles);
        return user;
    }

    public JwtDto login(Login login){
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateToken(authentication);
    }
}

