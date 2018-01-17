package com.test.app.Entity.User;

import com.alibaba.fastjson.JSONObject;

import javax.persistence.*;

@Entity
@Table
public class User{

    @Id
    @GeneratedValue
    private Long id;

    public User(){

    }

    public User(Integer age, String realName, String gender, String tel, String email, String fix, UserAuth auth, Role role) {
        this.age = age;
        this.realName = realName;
        this.gender = gender;
        this.tel = tel;
        this.email = email;
        this.fix = fix;
        this.auth = auth;
        this.role = role;
    }

    private Integer age;
    private String realName;
    private String gender;
    private String tel;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(nullable = true)
    private String fix;

    public void setAuth(UserAuth auth) {
        this.auth = auth;
    }

    @OneToOne(targetEntity = UserAuth.class, mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserAuth auth;

    @ManyToOne(targetEntity = Role.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "role")
    private Role role;

    public UserJob getUserJob() {
        return userJob;
    }

    public void setUserJob(UserJob userJob) {
        this.userJob = userJob;
    }

    @OneToOne(targetEntity = UserJob.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_job", nullable = true)

    private UserJob userJob;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRealName() {
        return realName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getFix() {
        return fix;
    }

    public void setFix(String fix) {
        this.fix = fix;
    }

    public String getUserAuth () {
        return auth.getAccount();
    }
    public UserAuth get() {
        return auth;
    }

//    public void setAuth(UserAuth auth) {
//        this.auth = auth;
//    }

}
