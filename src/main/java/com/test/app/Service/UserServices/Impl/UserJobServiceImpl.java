package com.test.app.Service.UserServices.Impl;

import com.test.app.Entity.User.UserJob;
import com.test.app.Repository.User.UserJobRepository;
import com.test.app.Service.UserServices.UserJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserJobServiceImpl implements UserJobService {
    @Autowired
    private UserJobRepository userJobRepository;

    public UserJobRepository getUserJobRepository() {
        return userJobRepository;
    }

    public void setUserJobRepository(UserJobRepository userJobRepository) {
        this.userJobRepository = userJobRepository;
    }

    public UserJobServiceImpl() {

    }

    @Override
    public List<UserJob> findAll() {
        return userJobRepository.findAll();
    }

    @Override
    public UserJob setOne(UserJob userJob) {
        return userJobRepository.saveAndFlush(userJob);
    }

    @Override
    public UserJob findOne(Long id) {
        return userJobRepository.findOne(id);
    }
}
