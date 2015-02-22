package com.hong.spring.common.dao;

import java.util.Collection;
import java.util.List;

import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableRecord;

public interface GenericDao<R extends TableRecord<R>, E, K> {

	void insert(Collection<E> objects);

	void update(Collection<E> objects);

	void delete(Collection<E> objects);

	void deleteById(Collection<K> ids);

	boolean exists(E object);

	boolean existsById(K id);

	long count();

	List<E> findAll();

	E findById(K id);

	@SuppressWarnings("unchecked")
	<Z> List<E> fetch(Field<Z> field, Z... values);

	<Z> E fetchOne(Field<Z> field, Z value);

	Table<R> getTable();

	Class<E> getType();

}