package com.ivymei.framework.util.db;

import java.lang.annotation.*;

/**
 * Created by 20170331 on 2017/12/12.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DataSource {

    String name() default DataSource.mainDataSource;

    public static String mainDataSource = "mainDataSource";

    public static String centerDataSource = "centerDataSource";
}
