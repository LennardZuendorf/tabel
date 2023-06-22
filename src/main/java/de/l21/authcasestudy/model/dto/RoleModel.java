package de.l21.authcasestudy.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoleModel {

    Long user_id;
    String role;
}
