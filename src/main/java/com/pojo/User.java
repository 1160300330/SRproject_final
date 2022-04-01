package com.pojo;

import lombok.Data;

@Data
public class User {
    private String id;
    private String name="未命名";
    private String password;
    private double count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public User(String id,String password,String name,double count){
        this.id = id;
        this.password = password;
        this.name = name;
        this.count = count;

    }
    public User(){


    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + password + '\'' +
                '}';
    }
}
