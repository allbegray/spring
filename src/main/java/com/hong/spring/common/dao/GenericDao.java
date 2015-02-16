package com.hong.spring.common.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<E> {

    E insert(E entity);

    int update(E entity);

    E getById(int id);

    List<E> getList();

    int deleteById(int id);

}