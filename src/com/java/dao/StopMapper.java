package com.java.dao;

import java.util.List;

import com.java.model.Stop;

public interface StopMapper {
    int Del(Integer id);

    int Add(Stop record);

    int Edit(Stop record);
    int EditEndTime(Stop record);

    Stop GetByID(Integer id);

    int GetCount(Stop record);
    int GetSum(Stop record);
    List<Stop> Get(Stop record);
}