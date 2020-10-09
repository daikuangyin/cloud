package com.sun.cloud.enums.constraint;


import com.sun.cloud.enums.CustomEnumValidator;
import com.sun.cloud.enums.DictEnum;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomEnumValidator.class)
public @interface CustomConstraint {
    Class<? extends DictEnum> value();

    String message() default "非枚举值";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
