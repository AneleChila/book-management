package com.payu.config.logging;

import java.lang.annotation.*;

/**
 * @author Anele Chila
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface AroundLogger {

}