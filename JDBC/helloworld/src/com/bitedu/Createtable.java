package com.bitedu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Createtable {
    public static void main(String[] args) {
        createTable();
    }

    public static Connection getconnection(){
        Connection connection=null;
        Statement stmt=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost/my_jdbc_1","root","");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void createTable(){
        Statement stmt=null;
        try {
            stmt=getconnection().createStatement();
            String sql="create table student2(name varchar (20),id int)";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt!=null){
                    stmt.close();
                }
                if(getconnection()!=null){
                    getconnection().close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
