package com.example.application.service;

import com.example.application.dao.DvDDao;
import com.example.application.entity.DVD;
import com.example.application.exception.DaoException;
import com.example.application.utils.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DvDServiceImpl implements DvDService{
    @Resource
    private DvDDao dvdDao;

    @Override
    public void saveDvD(DVD dvd) throws DaoException {
        if (dvd == null) {
            System.err.println("DVD is null!");
            return;
        }
        System.out.println("++++++++++++id: "+dvd.getId());
        if (dvd.getId() == null || dvd.getId() == "") {
            dvd.setId(UUIDUtil.getUUID());
            if (dvdDao.insert(dvd)!=1) throw new DaoException("Save DVD failed!");
        } else if (dvdDao.update(dvd)!=1) throw new DaoException("Update DVD failed!");

    }

    @Override
    public void deleteDvD(DVD dvd) throws DaoException {
        if (dvdDao.delete(dvd)!=1) throw new DaoException("Delete DVD failed!");
    }

    @Override
    public List<DVD> findAllDvDs(String title,String score) {
        return dvdDao.selectAll(title,score);
    }
}
