package com.likelion.dao;

import com.likelion.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.*;
import java.util.Map;

public class UserDao {

    private ConnectionMaker cm;
    private JdbcContext jdbcContext;

    public UserDao(ConnectionMaker cm) {
        this.cm = cm;
        this.jdbcContext = new JdbcContext(cm);
    }

    public void add(User user) {
        jdbcContext.workWithStatementStrategy(new AddStrategy(user));
    }

    public User get(String id) throws SQLException {

        User user= null;

        Connection c = cm.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id=?"
        );
        ps.setString(1,id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            user = new User(rs.getString("id"),rs.getString("name"),rs.getString("password"));
        }

        rs.close();
        ps.close();
        c.close();

        if (user == null) {
            throw new EmptyResultDataAccessException(1);
        }

        return user;
    }

    public void deleteAll(){
        jdbcContext.workWithStatementStrategy(new DeleteAllStrategy());
    }

    public int getCount() throws SQLException {

        Connection c = cm.getConnection();
        PreparedStatement ps = c.prepareStatement(
                "SELECT count(*) from users"
        );
        ResultSet rs = ps.executeQuery();
        rs.next();
        int cnt = rs.getInt(1);

        rs.close();
        ps.close();
        c.close();

        return cnt;
    }
}
