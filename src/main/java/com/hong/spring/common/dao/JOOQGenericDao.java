package com.hong.spring.common.dao;

import com.google.common.base.CaseFormat;
import com.hong.spring.common.util.GenericUtils;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Sort;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("rawtypes")
public class JOOQGenericDao<E, T extends Table> implements GenericDao<E, T> {

    protected Class<E> entityClass;
    protected Table table;
    protected Field keyField;

    @SuppressWarnings("unchecked")
    public JOOQGenericDao() {
        this.entityClass = GenericUtils.getClassOfGenericTypeIn(getClass(), 0);
        try {
            table = (Table) GenericUtils.getClassOfGenericTypeIn(getClass(), 1).newInstance();

            String keyFieldName = ((TableField)table.getPrimaryKey().getFields().get(0)).getName();
            keyFieldName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, keyFieldName);

            keyField = ReflectionUtils.findField(entityClass, keyFieldName);
            ReflectionUtils.makeAccessible(keyField);

        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Autowired
    private DSLContext dsl;

    public DSLContext getDsl() {
        return dsl;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E insert(E entity) {
        Record record = dsl.newRecord(table, entity);
        return dsl.insertInto(table).set(record).returning().fetchOne().into(entityClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public int update(E entity) {
        Object valueOfKey = null;
        try {
            valueOfKey = keyField.get(entity);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        Record record = dsl.newRecord(table, entity);
        return dsl.update(table).set(record).where(table.getIdentity().getField().eq(valueOfKey)).execute();
    }

    @SuppressWarnings("unchecked")
    @Override
    public E getById(Serializable id) {
        Record record = dsl.selectFrom(table).where(table.getIdentity().getField().eq(id)).fetchOne();
        return record != null ? record.into(entityClass) : null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> getList() {
        return dsl.selectFrom(table).fetch().into(entityClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public int deleteById(Serializable id) {
        return dsl.delete(table).where(table.getIdentity().getField().eq(id)).execute();
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
            Field tableField = table.getClass().getField(sortFieldName);
            sortField = (TableField) tableField.get(table);
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