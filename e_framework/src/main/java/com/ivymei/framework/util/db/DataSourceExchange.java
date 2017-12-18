package com.ivymei.framework.util.db;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author show
 * @date 2017/12/12
 */
public class DataSourceExchange implements MethodBeforeAdvice, AfterReturningAdvice {

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        DataSourceContextHolder.clearDataSource();
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        // 这里DataSource是自定义的注解，不是java里的DataSource接口
        boolean isSuperClassAnnotation = false;

        Class clazz = method.getDeclaringClass();
        Class parentClazz = clazz.getSuperclass();

        if (method.getDeclaringClass().getSuperclass() != null) {
            isSuperClassAnnotation = method.getDeclaringClass().getSuperclass().isAnnotationPresent(DataSource.class);
        }
        boolean isClassAnnotation = method.getDeclaringClass().isAnnotationPresent(DataSource.class);
        boolean isMethodAnnotation = method.isAnnotationPresent(DataSource.class);

        // 数据源的选取优先级依次为：method > class > parentClass
        if (isMethodAnnotation) {
            DataSource annotation = method.getAnnotation(DataSource.class);
            DataSourceContextHolder.setDataSource(annotation.name());
        } else if (isClassAnnotation) {
            DataSource annotation = method.getDeclaringClass().getAnnotation(DataSource.class);
            DataSourceContextHolder.setDataSource(annotation.name());
        } else if (isSuperClassAnnotation) {
            DataSource annotation = method.getDeclaringClass().getSuperclass().getAnnotation(DataSource.class);
            DataSourceContextHolder.setDataSource(annotation.name());
        } else {
            DataSourceContextHolder.setDataSource(DataSource.mainDataSource);
        }
    }

    public static void main(String[] args) {
        boolean a = true;
        boolean b = true;
        boolean c = true;
        if (a) {
            System.out.println("AAA");
        } else if (b) {
            System.out.println("BBB");

        } else if (c) {
            System.out.println("CCC");

        } else {

            System.out.println("DDD");
        }
    }
}
