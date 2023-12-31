package de.l21.authcasestudy.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Objects;
import java.util.UUID;

@ToString
@RequiredArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "ROLE")
@AllArgsConstructor
public class RoleEntity {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @ToString.Exclude
    UserEntity user;

    @Column(name = "ROLE", nullable = false)
    String role;

}
