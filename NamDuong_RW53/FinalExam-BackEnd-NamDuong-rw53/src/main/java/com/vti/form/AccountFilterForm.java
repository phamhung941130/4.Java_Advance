package com.vti.form;

import com.vti.entity.Account;
import com.vti.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountFilterForm {
    private String search;
    private Role role;
    private Integer minId;
    private Integer maxId;
}
