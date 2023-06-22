package de.l21.authcasestudy.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@ToString
@RequiredArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "USER")
@AllArgsConstructor
public class UserEntity {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name="EMAIL", nullable = false)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<RoleEntity> roles;

}
