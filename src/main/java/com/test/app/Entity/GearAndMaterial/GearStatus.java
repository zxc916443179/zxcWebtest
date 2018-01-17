package com.test.app.Entity.GearAndMaterial;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class GearStatus {
    @Id
    @GeneratedValue
    private Long id;
    private String fixId;
    private String type;
    private String unit;
    private String date;
    private String sponsor;
    private String details;
    private String operation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFixId() {
        return fixId;
    }

    public void setFixId(String fixId) {
        this.fixId = fixId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public GearStatus(String fixId, String type, String unit, String date, String sponsor, String details, String operation) {

        this.fixId = fixId;
        this.type = type;
        this.unit = unit;
        this.date = date;
        this.sponsor = sponsor;
        this.details = details;
        this.operation = operation;
    }

    public GearStatus() {

    }
}
