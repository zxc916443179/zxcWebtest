package com.test.app.Repository;

import com.test.app.Entity.GearAndMaterial.Gear;
import com.test.app.Entity.GearAndMaterial.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    @Query(value = "select m from Material m where m.code like %:value%")
    public List<Material> findByCode(@Param("value")String value);
    @Query(value = "select m from Material m where m.unit like %:value%")
    public List<Material> findByUnit(@Param("value")String value);
    @Query(value = "select m from Material m where m.result like %:value%")
    public List<Material> findByResult(@Param("value")String value);
}
