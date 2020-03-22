package com.jwm.chatroom.service;

import com.jwm.chatroom.mapper.UserMapper;
import com.jwm.chatroom.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    private Random random = new Random();

    //登录
    public User findByUsernameAndPassword(String username, String password) {
        return userMapper.findByUsernameAndPassword(username, password);
    }

    //注册
    public void save(User user) {
        userMapper.save(user);
    }

    //注册时差询名字是否重复
    public Integer findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

}
