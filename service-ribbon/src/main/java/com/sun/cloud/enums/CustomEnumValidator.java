package com.sun.cloud.enums;

import com.sun.cloud.enums.constraint.CustomConstraint;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomEnumValidator implements ConstraintValidator<CustomConstraint, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomEnumValidator.class);

    private Class<? extends DictEnum> clazz;

    @Override
    public void initialize(CustomConstraint arg0) {
        clazz = arg0.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext arg1) {
        if (StringUtils.isBlank(value)) {
            return true;
        }
        return DictEnum.exist(clazz, value);
    }

}
