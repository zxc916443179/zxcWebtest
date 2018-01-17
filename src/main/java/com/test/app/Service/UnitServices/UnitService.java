package com.test.app.Service.UnitServices;

import com.test.app.Entity.Sector.Unit;
import com.test.app.Entity.User.User;
import com.test.app.Entity.User.UserJob;

import java.util.List;

public interface UnitService {
    List<Unit> findAll();
    Unit setOne (Unit unit);
    Unit findOne (Long id);
    void deleteOne (Long id);
    List<Unit> findBy (String type, String value);
    List<Unit> getJuniorByName (String name);
    List<UserJob> getUsers (String name);
}
