package com.bitedu;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert1 {
    public static void main(String[] args) {
        insertTable();
    }

    public static Connection getconnection() throws SQLException {
        DataSource ds=new MysqlDataSource();
        ((MysqlDataSource) ds).setURL("jdbc:mysql://localhost/my_jdbc_1");
        ((MysqlDataSource) ds).setUser("root");
        ((MysqlDataSource) ds).setPassword("");
        Connection connection=ds.getConnection();
        return connection;
    }
    public static void insertTable(){
        Statement stat=null;
        try {
            stat=getconnection().createStatement();
            stat.execute("insert into student3 values ('tdd',01)");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(stat!=null){
                    stat.close();
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
