package com.test.app.Service.Impl;

import com.test.app.Entity.GearAndMaterial.*;
import com.test.app.Repository.*;
import com.test.app.Service.MaterialAndGearService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialAndGearServiceImpl implements MaterialAndGearService {
    private MaterialRepository m;
    private GearRepository g;
    private GearStatuRepository s;
    private MaterialCatalogRepository materialCatalogRepository;
    private MaterialMetaDataRepository materialMetaDataRepository;
    public MaterialCatalogRepository getMaterialCatalogRepository() {
        return materialCatalogRepository;
    }
    private GearMetaDataRepository gearMetaDataRepository;
    public void setMaterialCatalogRepository(MaterialCatalogRepository materialCatalogRepository) {
        this.materialCatalogRepository = materialCatalogRepository;
    }

    public MaterialMetaDataRepository getMaterialMetaDataRepository() {
        return materialMetaDataRepository;
    }

    public void setMaterialMetaDataRepository(MaterialMetaDataRepository materialMetaDataRepository) {
        this.materialMetaDataRepository = materialMetaDataRepository;
    }

    public GearMetaDataRepository getGearMetaDataRepository() {
        return gearMetaDataRepository;
    }

    public void setGearMetaDataRepository(GearMetaDataRepository gearMetaDataRepository) {
        this.gearMetaDataRepository = gearMetaDataRepository;
    }

    public MaterialAndGearServiceImpl(MaterialRepository m, GearRepository g, GearStatuRepository s, MaterialCatalogRepository materialCatalogRepository, MaterialMetaDataRepository materialMetaDataRepository, GearMetaDataRepository gearMetaDataRepository) {
        this.m = m;
        this.g = g;
        this.s = s;
        this.materialCatalogRepository = materialCatalogRepository;
        this.materialMetaDataRepository = materialMetaDataRepository;
        this.gearMetaDataRepository = gearMetaDataRepository;


    }

    public GearStatuRepository getS() {
        return s;
    }

    public void setS(GearStatuRepository s) {
        this.s = s;
    }

    @Override
    public List<Material> getAllMaterials() {
        return m.findAll();
    }

    @Override
    public List<Gear> getAllGears() {
        if (g.findAll() == null) {
            return null;
        }
        return g.findAll();
    }

    public GearRepository getG() {
        return g;
    }

    public void setG(GearRepository g) {
        this.g = g;
    }

    @Override
    public Gear setOne(Gear gear) {
        Gear g = (Gear)this.g.saveAndFlush(gear);
        return g;
    }

    public MaterialRepository getM() {
        return m;
    }

    public void setM(MaterialRepository m) {
        this.m = m;
    }


    @Override
    public List<GearStatus> findGearStatusBy(String type, String value) {
        if (type.equals("fixId")) {
            return s.findByFixId(value);
        } else if (type.equals("unit")) {
            return s.findByUnit(value);
        } else if (type.equals("type")) {
            return s.findByType(value);
        } else if (type.equals("sponsor")) {
            return s.findBySponsor(value);
        }
        return null;
    }

    @Override
    public List<Gear> findGearsBy(String type, String value) {
        if (type.equals("unit")) {
            return g.findByUnit(value);
        } else if (type.equals("longingUnit")) {
            return g.findByLongingUnit(value);
        } else if (type.equals("type")) {
            return g.findByType(value);
        } else if (type.equals("fixUnit")) {
            return g.findByFixUnit(value);
        } else if (type.equals("sponsor")) {
            return g.findBySponsor(value);
        } else return null;
    }

    @Override
    public List<Material> findMaterialsBy(String type, String value) {
        if (type.equals("code")) {
            return m.findByCode(value);
        } else if (type.equals("unit")) {
            return m.findByUnit(value);
        } else if (type.equals("result")) {
            return m.findByResult(value);
        } else return null;
    }

    @Override
    public List<GearStatus> getAllStatus() {
        List<GearStatus> list = s.findAll();
        return list;
    }

    @Override
    public Material findMaterialOneById(Long id) {
        return m.findOne(id);
    }

    @Override
    public List<MaterialMetaData> getAllMaterialMetas() {
        return materialMetaDataRepository.findAll();
    }

    @Override
    public List<MaterialMetaData> findMaterialMetaDataBy(String type, String value) {
        if (type.equals("number")) {
            return materialMetaDataRepository.findByNumber(value);
        } else if (type.equals("name")) {
            return materialMetaDataRepository.findByName(value);
        } else return null;
    }

    @Override
    public void remove(MaterialMetaData metaData) {
        materialMetaDataRepository.delete(metaData);
    }

    @Override
    public MaterialMetaData findMaterialMetaById(Long id) {
        return materialMetaDataRepository.findOne(id);
    }

    @Override
    public List<MaterialCatalog> findCatalogBy(String type, String value) {
        if (type.equals("name")) {
            return materialCatalogRepository.findByName(value);
        } else if (type.equals("level")) {
            return materialCatalogRepository.findByLevel(value);
        } else return null;
    }

    @Override
    public void remove(MaterialCatalog materialCatalog) {
        materialCatalogRepository.delete(materialCatalog);
    }

    @Override
    public MaterialCatalog setOne(MaterialCatalog materialCatalog) {
        return materialCatalogRepository.saveAndFlush(materialCatalog);
    }

    @Override
    public MaterialCatalog findCatalogById(Long id) {
        return materialCatalogRepository.findOne(id);
    }

    @Override
    public List<MaterialCatalog> getAllCatalogs() {
        return materialCatalogRepository.findAll();
    }

    @Override
    public GearStatus setOne(GearStatus gearStatus) {
        GearStatus gearStatus1 = (GearStatus)s.saveAndFlush(gearStatus);
        return  gearStatus1;
    }


    @Override
    public Material setOne(Material material) {
        Material material1= m.saveAndFlush(material);
        return material1;
    }

    @Override
    public void remove(GearMetaData gearMetaData) {
        gearMetaDataRepository.delete(gearMetaData);
    }

    @Override
    public List<GearMetaData> findGearMetaDataBy(String type, String value) {
        if (type.equals("name")) {
            return gearMetaDataRepository.findByNameLike(value);
        } else if (type.equals("model")) {
            return gearMetaDataRepository.findByModelLike(value);
        }
        return null;
    }

    @Override
    public GearMetaData findGearMetaDataById(Long id) {
        return gearMetaDataRepository.findOne(id);
    }

    @Override
    public GearMetaData setOne(GearMetaData gearMetaData) {
        return gearMetaDataRepository.saveAndFlush(gearMetaData);
    }

    @Override
    public GearMetaData findGearMetaDataByName(String name) {
        return gearMetaDataRepository.findByName(name);
    }

    @Override
    public List<GearMetaData> getAllGearMetas () {
        return gearMetaDataRepository.findAll();
    }
}
