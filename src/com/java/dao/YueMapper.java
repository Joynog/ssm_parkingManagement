package com.java.dao;

import java.util.List;

import com.java.model.Yue;

public interface YueMapper {
    int Del(Integer id);

    int Add(Yue record);

    int Edit(Yue record);
    int EditEndTime(Yue record);

    Yue GetByID(Integer id);

    int GetCount(Yue record);
    List<Yue> Get(Yue record);
}