package com.test.app.Repository.User;

import com.test.app.Entity.User.User;
import com.test.app.Entity.User.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long>{
    @Query(value = "select u from UserAuth u where u.account=:account and u.password=:password")
    public List<UserAuth> findByAccountAndPassword(@Param("account")String account, @Param("password")String password);

    @Query(value = "select u from UserAuth u where u.account=:account")
    public List<UserAuth> findByAccount(@Param("account") String account);
}
