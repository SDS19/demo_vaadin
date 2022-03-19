package com.example.application.dao;

import com.example.application.entity.DVD;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DvDDao {
    
    int insert(DVD dvd);

    int delete(DVD dvd);

    List<DVD> selectAll(@Param("title") String title,@Param("imdbScore") String score);

    int update(DVD dvd);
}
