package de.l21.authcasestudy.model.dao;

import de.l21.authcasestudy.model.dto.RoleEntity;
import de.l21.authcasestudy.model.dto.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

}
