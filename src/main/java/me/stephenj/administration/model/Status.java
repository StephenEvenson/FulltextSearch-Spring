package me.stephenj.administration.model;

import me.stephenj.administration.enums.LoginFailAtEnum;

public class Status {
    private Boolean login;
    private LoginFailAtEnum at;

    public Status() {
    }

    public Boolean getLogin() {
        return login;
    }

    public void setLogin(Boolean login) {
        this.login = login;
    }

    public LoginFailAtEnum getAt() {
        return at;
    }

    public void setAt(LoginFailAtEnum at) {
        this.at = at;
    }
}
