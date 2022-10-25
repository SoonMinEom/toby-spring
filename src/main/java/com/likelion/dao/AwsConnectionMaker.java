package com.likelion.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class AwsConnectionMaker implements ConnectionMaker{
    @Override
    public Connection getConnection() throws SQLException {
        Map<String, String> evn = System.getenv();
        Connection c = DriverManager.getConnection(evn.get("DB_HOST"),evn.get("DB_NAME"),evn.get("DB_PASSWORD"));

        return c;
    }
}
