package com.javahelps.com.javahelps.interactive_e_learning_app.ui.login;

public class User {
    private String user_id;
    private String password;
    private String user_type;

    public User() {
    }

    public User(String user_id, String password, String user_type) {
        this.user_id = user_id;
        this.password = password;
        this.user_type = user_type;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
}
