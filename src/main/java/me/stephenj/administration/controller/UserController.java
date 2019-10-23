package me.stephenj.administration.controller;

import me.stephenj.administration.mapper.UserMapper;
import me.stephenj.administration.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public void add(User user) {
        userMapper.insert(user);
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @RequestMapping(value = "user", method = RequestMethod.PUT)
    public void update(User user) {
        userMapper.update(user);
    }

    @RequestMapping(value = "user/{user}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("user") String user) {
        userMapper.delete(user);
    }
}
