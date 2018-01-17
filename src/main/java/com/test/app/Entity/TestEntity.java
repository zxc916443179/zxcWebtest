package com.test.app.Entity;

import javax.persistence.*;

@Entity
@Table(name = "test")
public class TestEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private  String name;
    @Column(name = "age")
    private Integer age;

    public TestEntity() {

    }
    public TestEntity(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
