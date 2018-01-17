package com.test.app.Repository;

import com.test.app.Entity.GearAndMaterial.GearMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GearMetaDataRepository extends JpaRepository<GearMetaData, Long>{
    public GearMetaData findByName(String name);
    @Query(value = "select g from GearMetaData g where g.name like %:value%")
    public List<GearMetaData> findByNameLike (@Param("value")String name);
    @Query(value = "select g from GearMetaData g where g.name like %:value%")
    public List<GearMetaData> findByModelLike (@Param("value")String value);
}
