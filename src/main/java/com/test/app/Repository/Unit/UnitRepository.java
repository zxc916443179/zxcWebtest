package com.test.app.Repository.Unit;

import com.test.app.Entity.Sector.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    @Query(value = "select u from Unit u where u.number like %:value%")
    public List<Unit> findByNumber(@Param("value")String value);
    @Query(value = "select u from Unit u where u.name like %:value%")
    public List<Unit> findByName(@Param("value")String value);
}
