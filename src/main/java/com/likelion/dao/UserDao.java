package com.likelion.dao;

import com.likelion.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class UserDao {

    private JdbcTemplate jdbcTemplate;

    private ConnectionMaker cm;
    private JdbcContext jdbcContext;

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jdbcContext = new JdbcContext(dataSource);
    }

    public UserDao(ConnectionMaker cm) {
        this.cm = cm;
        this.jdbcContext = new JdbcContext(cm);
    }

    public void add(User user) {
        jdbcTemplate.update("insert into users(id, name, password) value(?,?,?)",user.getId(),user.getName(),user.getPassword());
    }
    public void deleteAll(){
        jdbcTemplate.update("DELETE FROM users");
    }
    public int getCount() throws SQLException {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM users",Integer.class);
    }


    RowMapper<User> rowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(rs.getString("id"),rs.getString("name"),rs.getString("password"));
            return user;
        }
    };

    public User get(String id) throws SQLException {
        String sql = "SELECT * FROM users where id =?";
        return jdbcTemplate.queryForObject(sql,rowMapper,id);
    }

    public List<User> getAll() {
        String sql = "SELECT * FROM users order by id";
        return jdbcTemplate.query(sql,rowMapper);
    }



}
