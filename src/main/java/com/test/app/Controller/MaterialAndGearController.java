package com.test.app.Controller;

import com.alibaba.fastjson.JSONObject;
import com.test.app.Entity.GearAndMaterial.GearMetaData;
import com.test.app.Entity.GearAndMaterial.MaterialCatalog;
import com.test.app.Entity.GearAndMaterial.MaterialMetaData;
import com.test.app.Service.MaterialAndGearService;
import com.test.app.Utils.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping(value = "/springboot")
public class MaterialAndGearController {
    @Autowired
    private MaterialAndGearService materialAndGearService;
    @RequestMapping(value = "/material/getAllCatalogs")
    @ResponseBody
    public Object GetAllCatalogs () throws Exception {
        List<MaterialCatalog> list = materialAndGearService.getAllCatalogs();
        return Json.parseJson(list);
    }
    @RequestMapping(value = "/material/addCatalog")
    @ResponseBody
    Object AddCatalog (@RequestBody JSONObject form) throws Exception {
        String name = form.getString("name");
        Long id = form.getLong("superiorId");
        String level = form.getString("level");
        MaterialCatalog materialCatalog = new MaterialCatalog();
        materialCatalog.setLevel(level);
        materialCatalog.setName(name);
        if (id != 0) {
            MaterialCatalog superior = materialAndGearService.findCatalogById(id);
            superior.addCatatoList(materialCatalog);
            materialCatalog.setSuperior(superior);
        } else {
            materialCatalog.setSuperior(null);
        }
        materialAndGearService.setOne(materialCatalog);
        return null;
    }
    @RequestMapping(value = "/material/deleteCatalog")
    @ResponseBody
    Object DeleteCatalog (@RequestBody JSONObject jsonObject) throws Exception {
        Long id = jsonObject.getLong("id");
        if (materialAndGearService.findCatalogById(id) == null) {
            return null;
        }
        MaterialCatalog materialCatalog = materialAndGearService.findCatalogById(id);
        if (materialCatalog.getSuperior() != null) {
            MaterialCatalog superior = materialCatalog.getSuperior();
            superior.removeCatafromList(materialCatalog);
            materialAndGearService.setOne(superior);
        }
        materialAndGearService.remove(materialCatalog);
        return null;
    }
    @RequestMapping(value = "/material/findCatalogBy")
    @ResponseBody
    Object FindCatalogBy (@RequestBody JSONObject jsonObject) throws  Exception {
        List<MaterialCatalog> list = materialAndGearService.findCatalogBy(jsonObject.getString("type"), jsonObject.getString("value"));
        return Json.parseJson(list);
    }

    @RequestMapping(value = "/material/getAllMaterialMetaData")
    @ResponseBody
    Object GetMaterialMeta () throws Exception {
        List<MaterialMetaData> materialMetaData = materialAndGearService.getAllMaterialMetas();
        return Json.parseJson(materialMetaData);
    }
    @RequestMapping(value = "/material/addMaterialMetaData")
    @ResponseBody
    Object AddMaterialMeta (@RequestBody JSONObject form) throws Exception {
        MaterialCatalog superior = materialAndGearService.findCatalogById(form.getLong("superiorId"));
        MaterialMetaData metaData = new MaterialMetaData();
        superior.addMaterial(metaData);
        metaData.setName(form.getString("name"));
        metaData.setNumber(form.getString("number"));
        metaData.setPic(form.getString("pic"));
        metaData.setCatalog(superior);
        materialAndGearService.setOne(superior);
        return null;
    }

    @RequestMapping(value = "/material/deleteMaterialMetaData")
    @ResponseBody
    Object DeleteMaterialMeta (@RequestBody JSONObject form) throws Exception {
        MaterialMetaData metaData = materialAndGearService.findMaterialMetaById(form.getLong("id"));
        MaterialCatalog superior = metaData.getCatalog();
        superior.removeMaterial(metaData);
        materialAndGearService.setOne(superior);
        materialAndGearService.remove(metaData);
        return null;
    }

    @RequestMapping(value = "/material/updateMaterialMetaData")
    @ResponseBody
    Object UpdateMaterialMeta (@RequestBody JSONObject form) throws Exception {
        MaterialMetaData metaData = materialAndGearService.findMaterialMetaById(form.getLong("id"));
        MaterialCatalog old = metaData.getCatalog();
        old.removeMaterial(metaData);
        materialAndGearService.setOne(old);
        MaterialCatalog superior = materialAndGearService.findCatalogById(form.getLong("superiorId"));
        metaData.setCatalog(superior);
        superior.addMaterial(metaData);
        metaData.setPic(form.getString("pic"));
        metaData.setName(form.getString("name"));
        metaData.setNumber(form.getString("number"));
        materialAndGearService.setOne(superior);
        return null;
    }

    @RequestMapping(value = "/material/findMaterialMetaDataBy")
    @ResponseBody
    Object FindMaterialMetaDataBy (@RequestBody JSONObject form) throws Exception {
        List<MaterialMetaData> list = materialAndGearService.findMaterialMetaDataBy(form.getString("type"), form.getString("value"));
        return Json.parseJson(list);
    }

    @RequestMapping(value = "/gear/getGearMetaData")
    @ResponseBody
    Object GetAllGearMetas () throws Exception {
        List<GearMetaData> list = materialAndGearService.getAllGearMetas();
        return Json.parseJson(list);
    }

    @RequestMapping(value = "/gear/addGearMetaData")
    @ResponseBody
    Object AddGearMeta (@RequestBody JSONObject form) throws Exception {
        if (materialAndGearService.findGearMetaDataByName(form.getString("name")) != null) {
            throw new RETCode(104);
        }
        GearMetaData gearMetaData = JSONObject.toJavaObject(form, GearMetaData.class);
        materialAndGearService.setOne(gearMetaData);
        return null;
    }
    @RequestMapping(value = "/gear/deleteGearMetaData")
    @ResponseBody
    Object DeleteGearMetaData (@RequestBody JSONObject form) throws Exception {
        GearMetaData metaData = materialAndGearService.findGearMetaDataById(form.getLong("id"));
        materialAndGearService.remove(metaData);
        return null;
    }
    @RequestMapping(value = "/gear/updateGearMetaData")
    @ResponseBody
    Object UpdateGearMetaData (@RequestBody JSONObject form) throws Exception {
        GearMetaData metaData = materialAndGearService.findGearMetaDataById(form.getLong("id"));
        metaData.setModel(form.getString("model"));
        metaData.setName(form.getString("name"));
        metaData.setNumber(form.getString("number"));
        materialAndGearService.setOne(metaData);
        return null;
    }
    @RequestMapping(value = "/gear/findGearMetaDataBy")
    @ResponseBody
    Object FindGearMetaData (@RequestBody JSONObject form) throws Exception {
        List<GearMetaData> metaDatas = materialAndGearService.findGearMetaDataBy(form.getString("type"), form.getString("value"));
        return Json.parseJson(metaDatas);
    }
     /**
     * Exception handle
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public RETCode handleException (Exception e) {
        e.printStackTrace();
        if (e instanceof RETCode) {
            return (RETCode)e;
        } else if (e instanceof SQLException) {
            return new RETCode(102);
        } else {
            return new RETCode(100);
        }
    }

}
