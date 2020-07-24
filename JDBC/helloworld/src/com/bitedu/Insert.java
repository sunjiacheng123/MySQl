package com.bitedu;

import java.sql.*;

public class Insert {


    public static void insertTable(){
        Connection connection=null;
        Statement stat=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost/my_jdbc_1","root","");
            stat=connection.createStatement();
            stat.execute("insert into student3 values ('pdd',01)");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(stat!=null){
                    stat.close();
                }
                if(connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void select(){
        Connection connection=null;
        Statement stat=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost/my_jdbc_1","root","");
            stat=connection.createStatement();
            String sql=("select * from student3");
            rs=stat.executeQuery(sql);
            while (rs.next()) {
                String str=rs.getString("name");
                Integer a=rs.getInt("id");
                System.out.println("name="+str + "  "+ "id="+a);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(stat!=null){
                    stat.close();
                }
                if(connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //createTable();
        //insertTable();
        select();
    }
}
