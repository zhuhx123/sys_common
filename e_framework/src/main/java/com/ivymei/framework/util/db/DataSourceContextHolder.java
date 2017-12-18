package com.ivymei.framework.util.db;

/**
 * Created by 20170331 on 2017/12/12.
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    /**
     * @param dataSource 数据源名称
     * @return void
     * @throws
     * @Description: 设置数据源类型
     */
    public static void setDataSource(String dataSource) {
        contextHolder.set(dataSource);
    }

    /**
     * @param
     * @return String
     * @throws
     * @Description: 获取数据源名称
     */
    public static String getDataSource() {
        return contextHolder.get();
    }

    /**
     * @param
     * @return void
     * @throws
     * @Description: 清除数据源名称
     */
    public static void clearDataSource() {
        contextHolder.remove();
    }
}
