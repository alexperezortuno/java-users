package com.glign.backend.component;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = com.glign.backend.component.PasswordPatternValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordPattern {
    String message() default "the password format is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
