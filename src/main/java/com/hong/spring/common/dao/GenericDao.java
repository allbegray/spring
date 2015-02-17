package com.hong.spring.common.dao;

import java.util.Collection;
import java.util.List;

import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableRecord;

public interface GenericDao<R extends TableRecord<R>, P, T> {

	void insert(P entity);

	@SuppressWarnings("unchecked")
	void insert(P... objects);

	void insert(Collection<P> objects);

	void update(P entity);

	@SuppressWarnings("unchecked")
	void update(P... objects);

	void update(Collection<P> objects);

	@SuppressWarnings("unchecked")
	void delete(P... objects);

	void delete(Collection<P> objects);

	@SuppressWarnings("unchecked")
	void deleteById(T... ids);

	void deleteById(Collection<T> ids);

	boolean exists(P object);

	boolean existsById(T id);

	long count();

	List<P> findAll();

	P findById(T id);

	@SuppressWarnings("unchecked")
	<Z> List<P> fetch(Field<Z> field, Z... values);

	<Z> P fetchOne(Field<Z> field, Z value);

	Table<R> getTable();

	Class<P> getType();

}