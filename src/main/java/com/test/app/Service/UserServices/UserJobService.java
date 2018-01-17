package com.test.app.Service.UserServices;

import com.test.app.Entity.User.UserJob;

import java.util.List;

public interface UserJobService {
    List<UserJob> findAll ();
    UserJob findOne (Long id);
    UserJob setOne (UserJob userJob);
}
