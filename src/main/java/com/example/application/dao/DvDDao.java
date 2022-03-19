package com.example.application.dao;

import com.example.application.entity.DVD;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DvDDao {
    
    int insert(DVD dvd);

    int delete(DVD dvd);

    List<DVD> selectAll(String title);

    int update(DVD dvd);
}
