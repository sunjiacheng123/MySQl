package com.bitedu;

import com.sun.java.util.jar.pack.DriverResource_zh_CN;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



    public class Hello {
        public static void main(String[] args) {
            try {
                //1.加载数据库驱动：反射  将该类加载到JVM方法区中，并执行该类的静态方法块，静态属性
                Class.forName("com.mysql.jdbc.Driver");
                //2.获得连接
                //URL统一资源标识符
                Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/","root","");
                //3.创建操作命令
                Statement stmt=connection.createStatement();
                //4.执行SQL语句
                stmt.execute("create database my_jdbc_1");
                //stmt.execute("show databases()");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}

