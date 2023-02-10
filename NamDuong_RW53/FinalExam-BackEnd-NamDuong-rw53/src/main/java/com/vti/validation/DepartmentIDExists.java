package com.vti.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {DepartmentIDNotExistsValidator.class})
//@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(DepartmentIDExists.List.class)
public @interface DepartmentIDExists {
    String message() default "{Department.createDepartment.form.id.NotExists}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

//    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        DepartmentIDExists[] value();
    }
}