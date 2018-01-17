package com.test.app.Service.UserServices;

import com.test.app.Entity.User.UserAuth;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserAuthService {
    List<UserAuth> findByAccount (String account);
    UserAuth setOne (UserAuth userAuth);
}
