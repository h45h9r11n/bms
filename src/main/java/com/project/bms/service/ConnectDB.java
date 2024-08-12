package com.project.bms.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private String db_url;
    private String db_user;
    private String db_pass;

    public String getDb_url() {
        return db_url;
    }

    public void setDb_url(String db_url) {
        this.db_url = db_url;
    }

    public String getDb_user() {
        return db_user;
    }

    public void setDb_user(String db_user) {
        this.db_user = db_user;
    }

    public String getDb_pass() {
        return db_pass;
    }

    public void setDb_pass(String db_pass) {
        this.db_pass = db_pass;
    }

    public ConnectDB(){
        this.db_url = "jdbc:mariadb://localhost:3306/registration";
        this.db_user = "root";
        this.db_pass = "MYSql@123";
    }

    public Connection getConnection() {
        Connection connection = null;
        ConnectDB db = new ConnectDB();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(db.getDb_url(), db.getDb_user(), db.getDb_pass());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }



}
