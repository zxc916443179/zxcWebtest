package com.test.app.Service.UserServices.Impl;

import com.test.app.Entity.User.Role;
import com.test.app.Entity.User.User;
import com.test.app.Entity.User.UserAuth;
import com.test.app.Entity.User.UserJob;
import com.test.app.Repository.User.RoleRepository;
import com.test.app.Repository.User.UserAuthRepository;
import com.test.app.Repository.User.UserJobRepository;
import com.test.app.Repository.User.UserRepository;
import com.test.app.Service.UserServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserAuthRepository ua;
    @Autowired
    private UserRepository u;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserJobRepository userJobRepository;
    public UserServiceImpl(UserAuthRepository ua, UserRepository u, RoleRepository r, UserJobRepository userJobRepository) {
        this.ua = ua;
        this.u = u;
        this.roleRepository = r;
        this.userJobRepository = userJobRepository;
    }
    public UserServiceImpl() {
    }
    public UserAuthRepository getUa() {
        return ua;
    }

    public UserRepository getU() {
        return u;
    }

    public void setU(UserRepository u) {
        this.u = u;
    }

    @Override
    public void remove(User user) {
        u.delete(user);
    }

    public void setUa(UserAuthRepository ua) {
        this.ua = ua;
    }

    @Override
    public List<User> getUserByAuth_id(Integer auth_id) {
        return null;
    }



    @Override
    public User findById(Long id) {
        User user = u.findOne(id);
        return user;
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
    @Override
    public Role getRole (Long id) {
        return roleRepository.findOne(id);
    }

    @Override
    public User setOne(User user) {
        User user1 = u.saveAndFlush(user);
        return user1;
    }

    @Override
    public List<UserAuth> validate(String account, String password) {
        List<UserAuth> user = ua.findByAccountAndPassword(account, password);
        return user;
    }

    @Override
    public List<UserJob> findBy(String type, String value, String unit) {
        if (type.equals("realName")) {
           return userJobRepository.findByJob(value, unit);
        } else if (type.equals("role")) {
            List<UserJob> list = userJobRepository.findByUnit(unit);
            for (UserJob job : list) {
                User user = job.getUser();
                if (user.getRole().getRole().indexOf(value) == -1) {
                    list.remove(user);
                }
            }
            return list;
        } else if (type.equals("post")) {
            return userJobRepository.findByPost(value, unit);
        }
        return null;
    }
}
