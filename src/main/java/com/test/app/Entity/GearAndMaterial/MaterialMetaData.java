package com.test.app.Entity.GearAndMaterial;

import javax.persistence.*;

@Entity
@Table
public class MaterialMetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
            name = "generator",
            table = "mt_generator",
            pkColumnName = "gen_name",
            pkColumnValue = "MaterialMetaData_PK",
            valueColumnName = "gen_value",
            allocationSize = 1
    )
    private Long id;
    private String number;
    private String name;
    private String pic;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @ManyToOne(targetEntity = MaterialCatalog.class, cascade = {CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "catalog")
    private MaterialCatalog catalog;

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

    public MaterialCatalog getCatalog() {
        return catalog;
    }

    public void setCatalog(MaterialCatalog catalog) {
        this.catalog = catalog;
    }

    public MaterialMetaData(String number, String name, MaterialCatalog catalog) {

        this.number = number;
        this.name = name;
        this.catalog = catalog;
    }

    public MaterialMetaData() {

    }
}
