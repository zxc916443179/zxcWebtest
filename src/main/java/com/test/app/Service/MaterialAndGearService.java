package com.test.app.Service;

import com.test.app.Entity.GearAndMaterial.*;

import java.util.List;

public interface MaterialAndGearService {
    Material setOne (Material material);
    List<Material> getAllMaterials();
    List<Material> findMaterialsBy(String type, String value);
    Material findMaterialOneById (Long id);

    Gear setOne (Gear gear);
    List<Gear> getAllGears();
    List<Gear> findGearsBy(String type, String value);



    GearStatus setOne (GearStatus gearStatus);
    List<GearStatus> getAllStatus();
    List<GearStatus> findGearStatusBy(String type, String value);

    MaterialCatalog findCatalogById (Long id);
    List<MaterialCatalog> getAllCatalogs ();
    MaterialCatalog setOne (MaterialCatalog materialCatalog);
    void remove (MaterialCatalog materialCatalog);
    List<MaterialCatalog> findCatalogBy (String type, String value);

    List<MaterialMetaData> getAllMaterialMetas ();
    MaterialMetaData findMaterialMetaById (Long id);
    List<MaterialMetaData> findMaterialMetaDataBy (String type, String value);
    void remove (MaterialMetaData metaData);

    List<GearMetaData> getAllGearMetas ();
    GearMetaData findGearMetaDataByName (String name);
    GearMetaData setOne (GearMetaData gearMetaData);
    GearMetaData findGearMetaDataById (Long id);
    void remove (GearMetaData gearMetaData);
    List<GearMetaData> findGearMetaDataBy(String type, String value);
}
