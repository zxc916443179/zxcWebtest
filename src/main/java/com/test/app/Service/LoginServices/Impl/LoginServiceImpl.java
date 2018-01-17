package com.test.app.Service.LoginServices.Impl;

import com.test.app.Entity.Login.LoginRecords;
import com.test.app.Repository.Login.LoginRecordsRepository;
import com.test.app.Repository.Login.LoginRepository;
import com.test.app.Service.LoginServices.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginRepository l;
    @Autowired
    private LoginRecordsRepository loginRecordsRepository;
    @Override
    public List<LoginRecords> findRecordsBy(String type, String value) {
        if (type.equals("account")) {
            return loginRecordsRepository.findByAccount(value);
        } else if (type.equals("ip")) {
            return loginRecordsRepository.findByIp(value);
        } else return null;
    }

    public LoginRepository getL() {
        return l;
    }

    public void setL(LoginRepository l) {
        this.l = l;
    }

    public LoginServiceImpl() {

    }

    public LoginRecordsRepository getLoginRecordsRepository() {
        return loginRecordsRepository;
    }

    public void setLoginRecordsRepository(LoginRecordsRepository loginRecordsRepository) {
        this.loginRecordsRepository = loginRecordsRepository;
    }

    public LoginServiceImpl(LoginRepository l, LoginRecordsRepository loginRecordsRepository) {
        this.loginRecordsRepository = loginRecordsRepository;

        this.l = l;
    }

    @Override
    public List<LoginRecords> getAllRecords() {
        return loginRecordsRepository.findAll();
    }

    @Override
    public LoginRecords setOne(LoginRecords loginRecords) {
        return l.saveAndFlush(loginRecords);
    }
}
