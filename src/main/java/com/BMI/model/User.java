package com.BMI.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public User() {

    }

    public User (String username, String password) {
        this.userId = 1L;
        this.username = username;
        this.password = password;
    }



    public long getUser_id() {
        return userId;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Tutorial [user_id=" + userId + ", username=" + username + ", password=" + password + "]";
    }
}
