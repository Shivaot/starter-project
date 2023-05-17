package com.starter.demo.repositories.role;

import com.starter.demo.domain.user.Role;
import com.starter.demo.domain.user.Role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName roleName);

    List<Role> findAllByNameIn(Set<RoleName> roleNameList);

    List<Role> findAllByName(RoleName roleName);
}
