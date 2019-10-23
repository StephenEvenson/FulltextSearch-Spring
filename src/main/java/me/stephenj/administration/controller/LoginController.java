package me.stephenj.administration.controller;

import me.stephenj.administration.model.Status;
import me.stephenj.administration.model.User;
import me.stephenj.administration.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangruntian
 */
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Status Login(User user) {
        return loginService.checkUser(user);
    }

}
