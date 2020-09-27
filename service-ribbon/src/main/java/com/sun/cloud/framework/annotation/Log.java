package com.sun.cloud.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

	/**
	 * 是否插入数据库
	 *
	 * @return
	 */
	boolean dataBase() default true;

	/**
	 * 是否打印body
	 *
	 * @return
	 */
	boolean printBody() default true;

	/**
	 * 是否打印Query
	 *
	 * @return
	 */
	boolean printQuery() default true;

	/**
	 * 备注
	 *
	 * @return
	 */
	String remark() default "";
}
