package com.vti.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {AccountIdNotExistsValidator.class})
//@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(AccountIDExists.List.class)
public @interface AccountIDExists {
    String message() default "{Account.createAccount.form.id.NotExists}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

//    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
    AccountIDExists[] value();
    }
}
