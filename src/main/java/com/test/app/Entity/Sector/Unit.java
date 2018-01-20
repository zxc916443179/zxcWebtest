package com.test.app.Entity.Sector;

import com.alibaba.fastjson.annotation.JSONField;
import com.test.app.Entity.User.*;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
            name = "generator",
            table = "mt_generator",
            pkColumnName = "gen_name",
            pkColumnValue = "Unit_PK",
            valueColumnName = "gen_value",
            allocationSize = 1
    )
    private Long id;
    private String number;
    private String name;
    @ManyToOne(targetEntity = Unit.class, cascade = {CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "superior", nullable = true)
    private Unit superior;
    @OneToMany(targetEntity = Unit.class, cascade = {CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @OrderColumn(name = "junior", nullable = true)
    private List<Unit> units;
    @OneToMany(targetEntity = UserJob.class, cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @OrderColumn(name = "users", nullable = true)
    private List<UserJob> users;

    public List<UserJob> findUsers() {
        return users;
    }

    public void setUsers(List<UserJob> users) {
        this.users = users;
    }

    public List<Unit> findUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    @OneToOne(targetEntity = UserJob.class, cascade = {CascadeType.REFRESH, CascadeType.PERSIST} , fetch = FetchType.EAGER)
    @JoinColumn(name = "responsible", nullable = true)
    private UserJob responsible;
    private String tel;
    private String fix;
    @Column(nullable = true)
    private boolean isBased;

    public boolean isBased() {
        return isBased;
    }

    public void setBased(boolean based) {
        isBased = based;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getSuperior() {
        return superior;
    }

    public void setSuperior(Unit superior) {
        this.superior = superior;
    }

    public UserJob getResponsible() {
        return responsible;
    }

    public void setResponsible(UserJob responsible) {
        this.responsible = responsible;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFix() {
        return fix;
    }

    public void setFix(String fix) {
        this.fix = fix;
    }

    public Unit(String number, String name, Unit superior, List<Unit> units, List<UserJob> users, UserJob responsible, String tel, String fix) {
        this.number = number;
        this.name = name;
        this.superior = superior;
        this.units = units;
        this.users = users;
        this.responsible = responsible;
        this.tel = tel;
        this.fix = fix;
    }

    public void addJunior (Unit unit) {
        units.add(unit);
    }
    public void removeJunior (Unit unit) {
        units.remove(unit);
    }
    public void addUser (UserJob userJob) {
        users.add(userJob);
    }
    public void removeUser (UserJob userJob) {
        users.remove(userJob);
    }
    public Unit() {

    }
}
