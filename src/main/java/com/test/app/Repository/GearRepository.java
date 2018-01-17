package com.test.app.Repository;

import com.test.app.Entity.GearAndMaterial.Gear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GearRepository extends JpaRepository<Gear, Long> {
    @Query(value = "select g from Gear g where g.unit like %:value%")
    public List<Gear> findByUnit (@Param("value")String value);
    @Query(value = "select g from Gear g where g.longingUnit like %:value%")
    public List<Gear> findByLongingUnit (@Param("value")String value);
    @Query(value = "select g from Gear g where g.type like %:value%")
    public List<Gear> findByType (@Param("value")String value);
    @Query(value = "select g from Gear g where g.fixUnit like %:value%")
    public List<Gear> findByFixUnit (@Param("value")String value);
    @Query(value = "select g from Gear g where g.sponsor like %:value%")
    public List<Gear> findBySponsor (@Param("value")String value);
}
