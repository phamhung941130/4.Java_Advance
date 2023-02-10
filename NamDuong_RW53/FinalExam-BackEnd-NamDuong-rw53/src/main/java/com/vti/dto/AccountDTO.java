package com.vti.dto;

import com.vti.entity.Account;
import com.vti.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO extends RepresentationModel<AccountDTO> {
    private int id;
    private String username;
    private String fullName;
    private Role role;
    private String departmentName;
}
