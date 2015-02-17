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
public class GenericServiceImpl<D extends JOOQGenericDao, P, T> implements GenericService<D, P, T> {

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
	public void insert(P entity) {
		dao.insert(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void insert(P... objects) {
		dao.insert(objects);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void insert(Collection<P> objects) {
		dao.insert(objects);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void update(P entity) {
		dao.update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void update(P... objects) {
		dao.update(objects);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void update(Collection<P> objects) {
		dao.update(objects);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void delete(P... objects) {
		dao.delete(objects);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void delete(Collection<P> objects) {
		dao.delete(objects);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void deleteById(T... ids) {
		dao.deleteById(ids);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void deleteById(Collection<T> ids) {
		dao.deleteById(ids);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public boolean exists(P object) {
		return dao.exists(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public boolean existsById(T id) {
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
	public List<P> findAll() {
		return dao.findAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public P findById(T id) {
		return (P) dao.findById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public <Z> List<P> fetch(Field<Z> field, Z... values) {
		return dao.fetch(field, values);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public <Z> P fetchOne(Field<Z> field, Z value) {
		return (P) dao.fetchOne(field, value);
	}

}