package com.zidanJmartKD.dbjson;

/**
 * @author Zidan Ramadhan
 * @author zidan.ramadhan@ui.ac.id
 * @version 1.0
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for {@link JsonDBEngine} to automatically load a {@link JsonTable}.
 * A reference to this must be a static field type.
 * @author Netlab Team
 * @version 0.1
 */
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD})
public @interface JsonAutowired
{
	Class<?> value();
	String filepath();
}