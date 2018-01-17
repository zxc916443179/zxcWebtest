package com.test.app.Repository.Login;

import com.test.app.Entity.Login.LoginRecords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginRecords, Long> {
}
