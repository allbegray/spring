package com.hong.spring.common.dao;

import com.google.common.base.CaseFormat;
import com.hong.spring.common.util.GenericUtils;
import org.jooq.*;
import org.jooq.impl.TableImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Sort;
import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("rawtypes")
public abstract class JOOQGenericDao<E, R extends Record> implements GenericDao<E> {

    protected Class<E> entityClass;
    private java.lang.reflect.Field entityKeyField;

    public abstract Field<Integer> getKeyField();

    public abstract TableImpl<R> getTable();

    @Autowired
    private DSLContext dsl;

    public DSLContext getDsl() {
        return dsl;
    }

    @SuppressWarnings("unchecked")
    public JOOQGenericDao() {
        this.entityClass = GenericUtils.getClassOfGenericTypeIn(getClass(), 0);
        String keyFieldName = this.getKeyField().getName();
        keyFieldName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, keyFieldName);

        entityKeyField = ReflectionUtils.findField(entityClass, keyFieldName);
        ReflectionUtils.makeAccessible(entityKeyField);
    }

    @SuppressWarnings("unchecked")
    @Override
    public E insert(E entity) {
        Record record = dsl.newRecord(this.getTable(), entity);
        return dsl.insertInto(this.getTable()).set(record).returning().fetchOne().into(entityClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public int update(E entity) {
        Integer valueOfKey = null;
        try {
            valueOfKey = (Integer) entityKeyField.get(entity);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        Record record = dsl.newRecord(this.getTable(), entity);
        return dsl.update(this.getTable()).set(record).where(this.getKeyField().eq(valueOfKey)).execute();
    }

    @SuppressWarnings("unchecked")
    @Override
    public E getById(int id) {
        Record record = dsl.selectFrom(this.getTable()).where(this.getKeyField().eq(id)).fetchOne();
        return record != null ? record.into(entityClass) : null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> getList() {
        return dsl.selectFrom(this.getTable()).fetch().into(entityClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public int deleteById(int id) {
        return dsl.delete(this.getTable()).where(this.getKeyField().eq(id)).execute();
    }

    public Collection<SortField<?>> getSortFields(Sort sortSpecification) {
        Collection<SortField<?>> querySortFields = new ArrayList<>();

        if (sortSpecification == null) {
            return querySortFields;
        }

        Iterator<Sort.Order> specifiedFields = sortSpecification.iterator();

        while (specifiedFields.hasNext()) {
            Sort.Order specifiedField = specifiedFields.next();

            String sortFieldName = specifiedField.getProperty();
            Sort.Direction sortDirection = specifiedField.getDirection();

            TableField tableField = getTableField(sortFieldName);
            SortField<?> querySortField = convertTableFieldToSortField(tableField, sortDirection);
            querySortFields.add(querySortField);
        }

        return querySortFields;
    }

    private TableField getTableField(String sortFieldName) {
        TableField sortField = null;
        try {
            java.lang.reflect.Field tableField = this.getTable().getClass().getField(sortFieldName);
            sortField = (TableField) tableField.get(this.getTable());
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            String errorMessage = String.format("Could not find table field: {}", sortFieldName);
            throw new InvalidDataAccessApiUsageException(errorMessage, ex);
        }

        return sortField;
    }

    private SortField<?> convertTableFieldToSortField(TableField tableField, Sort.Direction sortDirection) {
        if (sortDirection == Sort.Direction.ASC) {
            return tableField.asc();
        } else {
            return tableField.desc();
        }
    }

}