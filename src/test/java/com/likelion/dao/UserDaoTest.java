package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    UserDao userDao;
    User user1;
    User user2;
    User user3;


    @BeforeEach
    void setUP() {
        userDao = new UserDao(new AwsConnectionMaker());
        user1 = new User("1","1111","1111");
        user2 = new User("2","2222","2222");
        user3 = new User("3","3333","3333");
    }

    @Test
    void addAndGet() throws SQLException {
        userDao.add(user2);
        assertEquals("1111",userDao.get("1").getName());
    }
}