package com.starter.demo.commandlinerunners;

import com.starter.demo.domain.user.Role;
import com.starter.demo.domain.user.User;
import com.starter.demo.domain.user.Role.RoleName;
import com.starter.demo.repositories.role.RoleRepository;
import com.starter.demo.repositories.user.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

@Log4j2
@Component
public class UserDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDataLoader(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {
        createRoles();
        createAdminUser();
    }

    private void createAdminUser() {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setName("Admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setUsername("admin");
            user.setRoles(new HashSet<>(roleRepository.findAllByName(RoleName.ROLE_ADMIN)));
            userRepository.save(user);
            log.info("Admin user added");
        }
    }

    private void createRoles() {
        if (roleRepository.count() == 0) {
            roleRepository.saveAll(Arrays.stream(RoleName.values()).map(Role::new).collect(Collectors.toList()));
            log.info("Roles added");
        }
    }
}
