package com.test.app.Service.LoginServices;

import com.test.app.Entity.Login.LoginRecords;

import java.util.List;

public interface LoginService {
    LoginRecords setOne(LoginRecords loginRecords);
    List<LoginRecords> findRecordsBy (String type, String value);
    List<LoginRecords> getAllRecords ();
}
