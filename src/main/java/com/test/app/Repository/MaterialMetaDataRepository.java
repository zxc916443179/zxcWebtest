package com.test.app.Repository;

import com.test.app.Entity.GearAndMaterial.MaterialMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaterialMetaDataRepository extends JpaRepository<MaterialMetaData, Long> {
    @Query(value = "select m from MaterialMetaData m where m.number like %:value%")
    List<MaterialMetaData> findByNumber (@Param("value")String value);
    @Query(value = "select m from MaterialMetaData m where m.name like %:value%")
    List<MaterialMetaData> findByName (@Param("value")String value);
}
