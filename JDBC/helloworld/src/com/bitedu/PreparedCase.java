package com.bitedu;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


import javax.sql.DataSource;

public class PreparedCase {
    private final static String URL="jdbc:mysql://192.168.1.1/test";
    private final static String USER="root";
    private final static String PASSWORD="";

    static {

    }
    public static DataSource getdataSource(){
        MysqlDataSource dataSource=new MysqlDataSource();
        return dataSource;

    }

    public static void

    public static void main(String[] args) {

    }
}
