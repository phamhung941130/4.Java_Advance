package com.vti.form;


import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountUpdateForm {
    private int id;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private int departmentId;

}
