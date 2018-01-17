package com.test.app.Entity.GearAndMaterial;

import javax.persistence.*;

@Entity
@Table
public class GearMetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
            name = "generator",
            table = "mt_generator",
            pkColumnName = "gen_name",
            pkColumnValue = "GearMetaData_PK",
            valueColumnName = "gen_value",
            allocationSize = 1
    )
    private Long id;
    @Column(unique = true)
    private String name;
    private String model;
    private String number;

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public GearMetaData(String name, String model, String number) {
        this.name = name;

        this.model = model;
        this.number = number;
    }

    public GearMetaData() {

    }
}
