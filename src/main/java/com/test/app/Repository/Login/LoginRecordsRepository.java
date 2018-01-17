package com.test.app.Repository.Login;

import com.test.app.Entity.Login.LoginRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoginRecordsRepository extends JpaRepository<LoginRecords, Long>{
    @Query(value = "select l from LoginRecords l where l.account like %:value%")
    public List<LoginRecords> findByAccount(@Param("value")String value);
    @Query(value = "select l from LoginRecords l where l.ip like %:value%")
    public List<LoginRecords> findByIp(@Param("value")String value);
}
