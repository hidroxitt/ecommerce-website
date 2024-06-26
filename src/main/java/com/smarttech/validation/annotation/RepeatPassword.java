package com.smarttech.validation.annotation;

import com.smarttech.validation.handler.RepeatPasswordHandler;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = RepeatPasswordHandler.class
)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatPassword {
    String[] fields() default {"password", "repeatPassword"};
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
