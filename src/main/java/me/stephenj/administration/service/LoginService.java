package me.stephenj.administration.service;

import me.stephenj.administration.enums.LoginFailAtEnum;
import me.stephenj.administration.mapper.UserMapper;
import me.stephenj.administration.model.Status;
import me.stephenj.administration.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UserMapper userMapper;

    public Status checkUser(User user) {
        String password = userMapper.getByUser(user.getUser());
        Status status = new Status();
        status.setLogin(false);;
        status.setAt(LoginFailAtEnum.password);
        if (password != null) {
            if (password.equals(user.getPassword())) {
                status.setLogin(true);
            }
        } else {
            status.setAt(LoginFailAtEnum.user);
        }
        return status;
    }
}
