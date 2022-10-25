package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;

    UserDao userDao;
    User user1;
    User user2;
    User user3;


    @BeforeEach
    void setUP() throws SQLException {
        userDao = context.getBean("awsUserDao", UserDao.class);
        user1 = new User("1","1111","1111");
        user2 = new User("2","2222","2222");
        user3 = new User("3","3333","3333");
        userDao.deleteAll();
    }

    @Test
    void addAndGet() throws SQLException {
        userDao.add(user1);
        assertEquals("1111",userDao.get("1").getName());

        assertThrows(EmptyResultDataAccessException.class, ()-> {
            userDao.get("2");
        });
        userDao.deleteAll();
    }

    @Test
    void getCount() throws SQLException {
        userDao.getCount();

        userDao.add(user1);
        assertEquals(1,userDao.getCount());

        userDao.add(user2);
        assertEquals(2,userDao.getCount());

        userDao.add(user3);
        assertEquals(3,userDao.getCount());

        userDao.deleteAll();
    }

    @Test
    void getAll() {
        userDao.add(user1);
        userDao.add(user2);
        userDao.add(user3);

        List<User> users = userDao.getAll();
        assertEquals(3,users.size());
    }
}