package de.l21.authcasestudy.model.dao;

import de.l21.authcasestudy.model.dto.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
