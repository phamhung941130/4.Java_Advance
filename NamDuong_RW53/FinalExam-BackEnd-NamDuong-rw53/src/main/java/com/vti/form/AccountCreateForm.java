package com.vti.form;


import com.vti.entity.Account;
import com.vti.entity.Role;
import com.vti.validation.AccountUsernameNotExists;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class AccountCreateForm {
    @NotBlank(message = "{Account.createAccount.form.name.NotBlank}")
    @Length(max = 50, message = "{Account.createAccount.form.name.Length}")
    @AccountUsernameNotExists
    private String username;

    private String password;
    @NotBlank(message = "{Account.createAccount.form.name.NotBlank}")
    private String firstName;
    @NotBlank(message = "{Account.createAccount.form.name.NotBlank}")
    private String lastName;

    private Role role;

    private int departmentId;

}
