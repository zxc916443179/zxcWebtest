package com.test.app.Entity.User;

import com.test.app.Entity.Sector.Unit;

import javax.persistence.*;

@Entity
@Table
public class UserJob {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
            name = "generator",
            table = "mt_generator",
            pkColumnName = "gen_name",
            pkColumnValue = "UserJob_PK",
            valueColumnName = "gen_value",
            allocationSize = 1
    )
    private Long id;
    private String job; // 职务
    private String post; // 岗位
    private String fix; // 备注
    @OneToOne(targetEntity = User.class, cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @OneToOne(targetEntity = Unit.class, cascade = {CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "manager", nullable = true)
    private Unit manager;
    @ManyToOne(targetEntity = Unit.class, cascade = {CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "unit", nullable = true)
    private Unit unit;

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Unit getManager() {
        return manager;
    }

    public void setManager(Unit manager) {
        this.manager = manager;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getFix() {
        return fix;
    }

    public void setFix(String fix) {
        this.fix = fix;
    }

    public UserJob() {

    }

    public UserJob(String job, String post, String fix, User user, Unit manager, Unit unit) {
        this.job = job;
        this.post = post;
        this.fix = fix;
        this.user = user;
        this.unit = unit;
        this.manager = manager;
    }
}
