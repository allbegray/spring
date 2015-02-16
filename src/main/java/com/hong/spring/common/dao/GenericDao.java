package com.hong.spring.common.dao;

import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableRecord;

import java.util.Collection;
import java.util.List;

public interface GenericDao<R extends TableRecord<R>, P, T> {

    void insert(P entity);

    void insert(P... objects);

    void insert(Collection<P> objects);

    void update(P entity);

    void update(P... objects);

    void update(Collection<P> objects);

    void delete(P... objects);

    void delete(Collection<P> objects);

    void deleteById(T... ids);

    void deleteById(Collection<T> ids);

    boolean exists(P object);

    boolean existsById(T id);

    long count();

    List<P> findAll();

    P findById(T id);

    <Z> List<P> fetch(Field<Z> field, Z... values);

    <Z> P fetchOne(Field<Z> field, Z value);

    Table<R> getTable();

    Class<P> getType();

}