package com.zyt.dao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: JDBCTemplate
 * @author: Tao_zy
 * @date: 2023/5/8
 **/


//BaseDao 升级版  解决   ResultSet 赋值的问题。
public class JdbcTemplate {
    String url = "jdbc:mysql://127.0.0.1:3306/smbms";
    String userName = "root";
    String password = "zyt123";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    //获取 连接
    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // 原始JDBC
    // BaseDao  1.0 JDBC
    // JdbcTemplate == BaseDao


    //增删改
    //String... 可变数组
    public int executeUpdate(String sql, Object... param) {
        Connection connection = this.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < param.length; i++) {
                statement.setObject((i + 1), param[i]);
            }
            //3.执行sql 语句          执行对象
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 查询  分类  返回值，不能定死 ,动态的，动态的依据 参数
    public <T> T selectOne(Class<T> classType, String sql, Object... param) {
        List<T> list = this.selectList(classType, sql, param);
        if(list.size()>0){
            return  list.get(0);
        }else {
            return null;
        }
    }

    public <T> List selectList(Class<T> classType, String sql, Object... param) {
        Connection connection = this.getConnection();
        PreparedStatement statement = null;
        List<T> resultList = new ArrayList<T>();
        try {
            statement = connection.prepareStatement(sql);
            //设置参数
            for (int i = 0; i < param.length; i++) {
                statement.setObject((i + 1), param[i]);
            }
            //3.执行sql 语句          执行对象
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // 反射  创建对象  获得 类的属性和方法 赋值  获得表字段
                // 通过反射创建对象
                // Student student = new Student();
                T t = classType.newInstance();
                // 通过反射，获得所有属性，并赋值
                // 获取所有数据库的字段 ，反射赋值
                //getMetaData  Java  Class   connection 表名，字段名
                //结果集当中  字段名
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
                    Field field = classType.getDeclaredField(resultSetMetaData.getColumnLabel(i + 1));
                    field.setAccessible(true);
                    String fileName = field.getName();
                    Object val =  resultSet.getObject(fileName);
                    if(val!=null){
                        field.set(t,val );
                    }
                }
                resultList.add(t);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }


    // 查询  分类  返回值，不能定死 ,动态的，动态的依据 参数
    public int selectCount(String sql, String... param) {
        Connection connection = this.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            //设置参数
            for (int i = 0; i < param.length; i++) {
                statement.setObject((i + 1), param[i]);
            }
            //3.执行sql 语句          执行对象
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

}

