package org.example.controller;

import org.example.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {


    @RequestMapping(path = "/findAll")
    @ResponseBody
    public List<User> findAll() {
        List<User> users = new ArrayList<User>();
        User user1 = new User();
        user1.setUsername("杨过");
        user1.setPassword("123456");
        user1.setAge(18);
        user1.setSex("男");

        User user2 = new User();
        user2.setUsername("杨过");
        user2.setPassword("123456");
        user2.setAge(18);
        user2.setSex("男");

        User user3 = new User();
        user3.setUsername("杨过");
        user3.setPassword("123456");
        user3.setAge(18);
        user3.setSex("男");

        users.add(user1);
        users.add(user2);
        users.add(user3);
        return users;
    }

}
