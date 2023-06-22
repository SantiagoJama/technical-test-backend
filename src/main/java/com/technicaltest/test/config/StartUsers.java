package com.technicaltest.test.config;

import com.technicaltest.test.auth.models.dto.NewUser;
import com.technicaltest.test.auth.models.entities.Role;
import com.technicaltest.test.auth.models.enums.RoleName;
import com.technicaltest.test.auth.repositories.RoleRepository;
import com.technicaltest.test.auth.repositories.UserRepository;
import com.technicaltest.test.auth.services.UserService;
import com.technicaltest.test.handler.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class StartUsers  implements CommandLineRunner {

        private final RoleRepository roleRepository;
        private final UserRepository userRepository;
        private final UserService userService;

        @Override
        public void run(String... args) throws Exception {
            initRole();
            initUsers();
        }


        private void initRole() {
            if(roleRepository.count() > 0) return;
            Role userAdmin = new Role();
            userAdmin.setRoleName(RoleName.ROLE_ADMIN);
            roleRepository.save(userAdmin);
            Role userNormal = new Role();
            userNormal.setRoleName(RoleName.ROLE_USER);
            roleRepository.save(userNormal);
        }

        private void initUsers() throws BadRequestException {
            if (userRepository.count() > 0) return;
            NewUser user = new NewUser();
            user.setUsername("santiagojama");
            user.setPassword("123");
            user.setPasswordconfi("123");
            NewUser admin = new NewUser();
            admin.setUsername("admin");
            admin.setRoles(Set.of("ROLE_ADMIN"));
            admin.setPassword("admin");
            admin.setPasswordconfi("admin");
            userService.save(user);
            userService.save(admin);


    }
}
