package com.likelion.dao;

import com.likelion.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao {

    private ConnectionMaker cm;

    public UserDao(ConnectionMaker cm) {
        this.cm = cm;
    }

    public void add(User user) throws SQLException {

        Connection c = cm.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) value(?,?,?)"
        );
        ps.setString(1,user.getId());
        ps.setString(2,user.getName());
        ps.setString(3,user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws SQLException {

        Connection c = cm.getConnection();
        PreparedStatement ps = c.prepareStatement(
                "select * from users where id=?"
        );
        ps.setString(1,id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User(rs.getString("id"),rs.getString("name"),rs.getString("password"));

        return user;
    }

    public void deleteAll() throws SQLException {
        Connection c = cm.getConnection();
        PreparedStatement ps =c.prepareStatement(
                "DELETE FROM users"
        );
        ps.executeUpdate();

        ps.close();
        c.close();
    }
}
