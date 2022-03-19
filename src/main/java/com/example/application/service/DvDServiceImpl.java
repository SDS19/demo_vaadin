package com.example.application.service;

import com.example.application.dao.DvDDao;
import com.example.application.entity.DVD;
import com.example.application.exception.DaoException;
import com.example.application.utils.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

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
        dvd.setId(UUIDUtil.getUUID());
        if (dvdDao.insert(dvd)!=1) throw new DaoException("Save DVD failed!");
    }

    @Override
    public void deleteDvD(DVD dvd) throws DaoException {
        if (dvdDao.delete(dvd)!=1) throw new DaoException("Delete DVD failed!");
    }

    @Override
    public List<DVD> findAllDvDs(String title) {
        return dvdDao.selectAll(title);
    }
}
