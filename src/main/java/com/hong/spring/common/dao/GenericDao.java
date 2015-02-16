package com.hong.spring.common.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<E, T> {

    E insert(E entity);

    int update(E entity);

    E getById(Serializable id);

    List<E> getList();

    int deleteById(Serializable id);

}