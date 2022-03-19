package com.example.application.service;

import com.example.application.dao.DvDDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DvDServiceImpl implements DvDService{
    @Resource
    private DvDDao dvdDao;
}
