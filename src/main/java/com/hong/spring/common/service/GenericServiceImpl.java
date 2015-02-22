package com.hong.spring.common.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jooq.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hong.spring.common.dao.JOOQGenericDao;
import com.hong.spring.common.domain.Creatable;
import com.hong.spring.common.domain.Modifiable;
import com.hong.spring.common.util.GenericUtils;
import com.hong.spring.service.security.SecurityService;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@SuppressWarnings("rawtypes")
public class GenericServiceImpl<D extends JOOQGenericDao, E, K> implements GenericService<D, E, K> {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private SecurityService securityService;

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

	@Override
	@Transactional
	public void insert(E entity) {
		this.insert(singletonList(entity));
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void insert(E... objects) {
		this.insert(asList(objects));
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void insert(Collection<E> objects) {
		for (E e : objects) {
			if (e instanceof Creatable && !StringUtils.hasText(((Creatable) e).getCreateId())) {
				((Creatable) e).setCreateId(securityService.getCurrentMemberId());
			}
		}
		dao.insert(objects);
	}

	@Override
	@Transactional
	public void update(E entity) {
		this.update(singletonList(entity));
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void update(E... objects) {
		this.update(asList(objects));
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void update(Collection<E> objects) {
		for (E e : objects) {
			if (e instanceof Modifiable) {
				if (!StringUtils.hasText(((Modifiable) e).getModifierId())) {
					((Modifiable) e).setModifierId(securityService.getCurrentMemberId());
				}
				if (((Modifiable) e).getModifierDt() == null) {
					// FIXME : DB 에서 날짜를 가져오도록 수정하거나 DAO 레벨에서 DBMS 에 맞게 update 하도록수정
					((Modifiable) e).setModifierDt(new Date());
				}
			}
		}
		dao.update(objects);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void delete(E... objects) {
		this.delete(asList(objects));
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
		this.deleteById(asList(ids));
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