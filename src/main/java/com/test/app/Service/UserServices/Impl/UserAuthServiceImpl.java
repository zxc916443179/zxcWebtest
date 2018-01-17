package com.test.app.Service.UserServices.Impl;

import com.test.app.Entity.User.UserAuth;
import com.test.app.Repository.User.UserAuthRepository;
import com.test.app.Service.UserServices.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserAuthServiceImpl implements UserAuthService {
    @Autowired
    public UserAuthRepository userAuthRepository;

    @Override
    public UserAuth setOne(UserAuth userAuth) {
        return userAuthRepository.saveAndFlush(userAuth);
    }

    public UserAuthServiceImpl(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public List<UserAuth> findByAccount(String account) {
        List<UserAuth> list = userAuthRepository.findByAccount(account);
        return list;
    }
}
