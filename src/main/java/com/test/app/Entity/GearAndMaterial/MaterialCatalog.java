package com.test.app.Entity.GearAndMaterial;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class MaterialCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generator")
    @TableGenerator(
            name = "generator",
            table = "mt_generator",
            pkColumnName = "gen_name",
            pkColumnValue = "MaterialCatalog_PK",
            valueColumnName = "gen_value",
            allocationSize = 1
    )
    private Long id;
    @OneToMany(targetEntity = MaterialCatalog.class, cascade = {CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.PERSIST})
    @OrderColumn
    private List<MaterialCatalog> materialCatalogs;
    @ManyToOne(targetEntity = MaterialCatalog.class, cascade = {CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "superior")
    private MaterialCatalog superior;
    private String name;
    private String level;
    @OneToMany(targetEntity = MaterialMetaData.class, cascade = CascadeType.ALL)
    @OrderColumn
    private List<MaterialMetaData> materialMetaDatas;

    public MaterialCatalog(List<MaterialCatalog> materialCatalogs, MaterialCatalog superior, String name, String level, List<MaterialMetaData> materialMetaDatas) {
        this.materialCatalogs = materialCatalogs;
        this.superior = superior;
        this.name = name;
        this.level = level;
        this.materialMetaDatas = materialMetaDatas;
    }

    public List<MaterialMetaData> getMaterialMetaDatas() {

        return materialMetaDatas;
    }

    public void setMaterialMetaDatas(List<MaterialMetaData> materialMetaDatas) {
        this.materialMetaDatas = materialMetaDatas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MaterialCatalog> getMaterialCatalogs() {
        return materialCatalogs;
    }

    public void setMaterialCatalogs(List<MaterialCatalog> materialCatalogs) {
        this.materialCatalogs = materialCatalogs;
    }

    public MaterialCatalog getSuperior() {
        return superior;
    }

    public void setSuperior(MaterialCatalog superior) {
        this.superior = superior;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public MaterialCatalog() {
    }
    public void addCatatoList (MaterialCatalog materialCatalog) {
        materialCatalogs.add(materialCatalog);
    }
    public void removeCatafromList (MaterialCatalog materialCatalog) {
        materialCatalogs.remove(materialCatalog);
    }
    public void addMaterial (MaterialMetaData metaData) {
        materialMetaDatas.add(metaData);
    }
    public void removeMaterial (MaterialMetaData metaData){
        materialMetaDatas.remove(metaData);
    }
}
