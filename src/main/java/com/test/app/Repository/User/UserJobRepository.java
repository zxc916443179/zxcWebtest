package com.test.app.Repository.User;

import com.test.app.Entity.User.User;
import com.test.app.Entity.User.UserJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserJobRepository extends JpaRepository<UserJob, Long>{
    @Query(value = "select job from UserJob job left join job.user user inner join job.unit unit where user.realName like %:realName% and unit.name=:unit")
    public List<UserJob> findByJob(@Param("realName")String realName, @Param("unit")String unit);
    @Query(value = "select job from UserJob  job left join job.unit unit where unit.name=:unit")
    public List<UserJob> findByUnit(@Param("unit")String unit);
    @Query(value = "select job from UserJob job join job.unit unit where unit.name=:unit and job.post like %:post%")
    public List<UserJob> findByPost (@Param("post")String post, @Param("unit")String unit);
}
