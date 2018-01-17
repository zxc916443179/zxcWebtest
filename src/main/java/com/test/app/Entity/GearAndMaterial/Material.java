package com.test.app.Entity.GearAndMaterial;

import javax.persistence.*;

@Entity
@Table
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private String date;
    private String unit;
    private Integer quantity;
    private String result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Material(String code, String date, String unit, Integer quantity, String result) {

        this.code = code;
        this.date = date;
        this.unit = unit;
        this.quantity = quantity;
        this.result = result;
    }

    public Material() {

    }
}
