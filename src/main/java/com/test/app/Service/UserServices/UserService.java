package com.test.app.Service.UserServices;

import com.test.app.Entity.User.Role;
import com.test.app.Entity.User.User;
import com.test.app.Entity.User.UserAuth;
import com.test.app.Entity.User.UserJob;

import java.util.List;

public interface UserService{

    List<UserAuth> validate(String account, String password) ;
    List<User> getUserByAuth_id(Integer auth_id);
    User setOne (User user);
    User findById (Long id);
    void remove (User user);
    List<Role> getRoles ();
    Role getRole (Long id);
    List<UserJob> findBy (String type, String value, String unit);
}
