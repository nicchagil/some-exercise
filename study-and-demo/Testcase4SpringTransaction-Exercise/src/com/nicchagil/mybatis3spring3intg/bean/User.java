package com.nicchagil.mybatis3spring3intg.bean;

public class User {
    private Integer id;

    private String username;

    private String password;

    private String childhoodname;

    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getChildhoodname() {
        return childhoodname;
    }

    public void setChildhoodname(String childhoodname) {
        this.childhoodname = childhoodname == null ? null : childhoodname.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}