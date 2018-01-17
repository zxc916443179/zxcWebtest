package com.test.app.Repository;

import com.test.app.Entity.GearAndMaterial.MaterialCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaterialCatalogRepository extends JpaRepository<MaterialCatalog, Long> {
    @Query(value = "select m from MaterialCatalog m where m.name like %:name%")
    List<MaterialCatalog> findByName (@Param("name")String name);
    @Query(value = "select m from MaterialCatalog m where m.level like %:value")
    List<MaterialCatalog> findByLevel (@Param("value")String value);
}
