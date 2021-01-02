package com.javaschool.dao;

import java.util.List;

public interface GeneralDao<T>{
    List<T> getAll();
}
