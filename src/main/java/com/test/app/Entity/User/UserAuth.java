package com.test.app.Entity.User;

import com.test.app.Entity.User.User;

import javax.persistence.*;

@Entity
@Table
public class UserAuth {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "account", unique = true)
    String account;
    @Column(name = "password")
    String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserAuth() {

    }

    public UserAuth(String account, String password, User user) {
        this.account = account;
        this.password = password;
        this.user = user;
    }

    @JoinColumn(name = "user_id")
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User user;
}
