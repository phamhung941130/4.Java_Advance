package com.vti.form;

import com.vti.entity.Department;
import com.vti.entity.Type;
import com.vti.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentUpdateForm {
    private int id;
    private String name;
    private int totalMembers;
    private Type type;

}
