package com.psda.hydra.repository;


import com.psda.hydra.model.Role;
import com.psda.hydra.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);
}
