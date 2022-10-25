package com.likelion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {
    private ConnectionMaker cm;

    public JdbcContext(ConnectionMaker cm) {
        this.cm = cm;
    }

    public void workWithStatementStrategy(StatementStrategy st) {
        Connection c = null;
        PreparedStatement ps = null;

        try {

            c = cm.getConnection();
            ps = st.getStatement(c);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
