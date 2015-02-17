package com.hong.spring.common.service;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jooq.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.hong.spring.common.dao.JOOQGenericDao;
import com.hong.spring.common.util.GenericUtils;

@SuppressWarnings("rawtypes")
public class GenericServiceImpl<D extends JOOQGenericDao, E, K> implements GenericService<D, E, K> {

	@Autowired
	private ApplicationContext applicationContext;

	private Class<D> daoClass;

	@Autowired
	protected D dao;
		public D getDao() {
			return dao;
		}

	@SuppressWarnings("unchecked")
	public GenericServiceImpl() {
		this.daoClass = GenericUtils.getClassOfGenericTypeIn(getClass(), 0);
	}

	@PostConstruct
	public void setUpDao() {
		this.dao = this.applicationContext.getBean(daoClass);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void insert(E entity) {
		dao.insert(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void insert(E... objects) {
		dao.insert(objects);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void insert(Collection<E> objects) {
		dao.insert(objects);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void update(E entity) {
		dao.update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void update(E... objects) {
		dao.update(objects);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void update(Collection<E> objects) {
		dao.update(objects);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void delete(E... objects) {
		dao.delete(objects);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void delete(Collection<E> objects) {
		dao.delete(objects);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void deleteById(K... ids) {
		dao.deleteById(ids);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void deleteById(Collection<K> ids) {
		dao.deleteById(ids);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public boolean exists(E object) {
		return dao.exists(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public boolean existsById(K id) {
		return dao.existsById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public long count() {
		return dao.count();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<E> findAll() {
		return dao.findAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public E findById(K id) {
		return (E) dao.findById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public <Z> List<E> fetch(Field<Z> field, Z... values) {
		return dao.fetch(field, values);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public <Z> E fetchOne(Field<Z> field, Z value) {
		return (E) dao.fetchOne(field, value);
	}

}