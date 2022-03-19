package com.example.application.service;

import com.example.application.entity.DVD;
import com.example.application.exception.DaoException;

import java.util.List;

public interface DvDService {

    void saveDvD(DVD dvd) throws DaoException;

    void deleteDvD(DVD dvd) throws DaoException;

    List<DVD> findAllDvDs(String title);
}
