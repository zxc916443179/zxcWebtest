package com.test.app.Repository;

import com.test.app.Entity.GearAndMaterial.GearStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GearStatuRepository extends JpaRepository<GearStatus, Long> {
    @Query(value = "select g from GearStatus g where g.fixId like %:value%")
    public List<GearStatus> findByFixId (@Param("value")String value);

    @Query(value = "select g from GearStatus g where g.unit like %:value%")
    public List<GearStatus> findByUnit (@Param("value")String value);

    @Query(value = "select g from GearStatus g where g.sponsor like %:value%")
    public List<GearStatus> findBySponsor (@Param("value")String value);

    @Query(value = "select g from GearStatus g where g.type like %:value%")
    public List<GearStatus> findByType (@Param("value")String value);
}
