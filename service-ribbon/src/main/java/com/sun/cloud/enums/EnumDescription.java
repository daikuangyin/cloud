package com.sun.cloud.enums;

import java.lang.annotation.*;

/**
 * 枚举类的描述
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnumDescription {

    /**
     * 枚举名称
     */
    String value();

}
