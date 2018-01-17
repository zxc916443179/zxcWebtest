package com.test.app.Service.UnitServices.Impl;

import com.test.app.Entity.Sector.Unit;
import com.test.app.Entity.User.UserJob;
import com.test.app.Repository.Unit.UnitRepository;
import com.test.app.Service.UnitServices.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitRepository unitRepository;

    @Override
    public void deleteOne(Long id) {
        unitRepository.delete(id);
    }

    @Override
    public Unit findOne(Long id) {
        return unitRepository.findOne(id);
    }

    @Override
    public Unit setOne(Unit unit) {
        return unitRepository.saveAndFlush(unit);
    }

    @Override
    public List<Unit> findBy(String type, String value) {
        if (type.equals("number")) {
            return unitRepository.findByNumber(value);
        } else if (type.equals("name")) {
//            return null;
            return unitRepository.findByName(value);
        } else return null;
    }

    @Override
    public List<Unit> findAll() {
        return unitRepository.findAll();
    }

    @Override
    public List<UserJob> getUsers(String name) {
        return unitRepository.findByName(name).get(0).getUsers();
    }

    @Override
    public List<Unit> getJuniorByName(String name) {
        return unitRepository.findByName(name).get(0).getUnits();
    }
}
