package com.test.app.Entity.GearAndMaterial;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Gear {
    @Id
    @GeneratedValue
    private Long id;
    private String unit;
    private String longingUnit;
    private String type;
    private String number;
    private String bug;
    private String needs;
    private String means;
    private String fixUnit;
    private String sponsor;
    private String tel;
    private String date;
    private String pic;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLongingUnit() {
        return longingUnit;
    }

    public void setLongingUnit(String longingUnit) {
        this.longingUnit = longingUnit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBug() {
        return bug;
    }

    public void setBug(String bug) {
        this.bug = bug;
    }

    public String getNeeds() {
        return needs;
    }

    public void setNeeds(String needs) {
        this.needs = needs;
    }

    public String getMeans() {
        return means;
    }

    public void setMeans(String means) {
        this.means = means;
    }

    public String getFixUnit() {
        return fixUnit;
    }

    public void setFixUnit(String fixUnit) {
        this.fixUnit = fixUnit;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Gear(String unit, String longingUnit, String type, String number, String bug, String needs, String means, String fixUnit, String sponsor, String tel, String date, String pic) {
        this.unit = unit;
        this.longingUnit = longingUnit;
        this.type = type;
        this.number = number;
        this.bug = bug;
        this.needs = needs;
        this.means = means;
        this.fixUnit = fixUnit;
        this.sponsor = sponsor;
        this.tel = tel;
        this.date = date;
        this.pic = pic;
    }

    public Gear() {
    }
}
