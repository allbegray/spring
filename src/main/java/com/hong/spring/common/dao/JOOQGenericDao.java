package com.hong.spring.common.dao;

import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.jooq.impl.DSL.row;

@SuppressWarnings("rawtypes")
public abstract class JOOQGenericDao<R extends UpdatableRecord<R>, P, T> implements GenericDao<R, P, T> {

    private final Table<R> table;
    private final Class<P> type;

    @Autowired
    private DSLContext dsl;

    public DSLContext getDsl() {
        return dsl;
    }

    protected JOOQGenericDao(Table<R> table, Class<P> type) {
        this.table = table;
        this.type = type;
    }

    @Override
    public final void insert(P object) {
        insert(singletonList(object));
    }

    @Override
    public final void insert(P... objects) {
        insert(asList(objects));
    }

    @Override
    public final void insert(Collection<P> objects) {

        if (objects.size() > 1) {
            dsl.batchInsert(records(objects, false)).execute();
        } else if (objects.size() == 1) {
            records(objects, false).get(0).insert();
        }
    }

    @Override
    public final void update(P object) {
        update(singletonList(object));
    }

    @Override
    public final void update(P... objects) {
        update(asList(objects));
    }

    @Override
    public final void update(Collection<P> objects) {

        if (objects.size() > 1) {
            dsl.batchUpdate(records(objects, true)).execute();
        } else if (objects.size() == 1) {
            records(objects, true).get(0).update();
        }
    }

    @Override
    public final void delete(P... objects) {
        delete(asList(objects));
    }

    @Override
    public final void delete(Collection<P> objects) {
        List<T> ids = new ArrayList<T>();

        for (P object : objects) {
            ids.add(getId(object));
        }

        deleteById(ids);
    }

    @Override
    public final void deleteById(T... ids) {
        deleteById(asList(ids));
    }

    @Override
    public final void deleteById(Collection<T> ids) {
        Field<?>[] pk = pk();

        if (pk != null) {
            dsl.delete(table).where(equal(pk, ids)).execute();
        }
    }

    @Override
    public final boolean exists(P object) {
        return existsById(getId(object));
    }

    @Override
    public final boolean existsById(T id) {
        Field<?>[] pk = pk();

        if (pk != null) {
            return dsl
                    .selectCount()
                    .from(table)
                    .where(equal(pk, id))
                    .fetchOne(0, Integer.class) > 0;
        } else {
            return false;
        }
    }

    @Override
    public final long count() {
        return dsl
                .selectCount()
                .from(table)
                .fetchOne(0, Long.class);
    }

    @Override
    public final List<P> findAll() {
        return dsl
                .selectFrom(table)
                .fetch()
                .into(type);
    }

    @Override
    public final P findById(T id) {
        Field<?>[] pk = pk();
        R record = null;

        if (pk != null) {
            record = dsl
                    .selectFrom(table)
                    .where(equal(pk, id))
                    .fetchOne();
        }

        return record == null ? null : record.into(type);
    }

    @Override
    public final <Z> List<P> fetch(Field<Z> field, Z... values) {
        return dsl
                .selectFrom(table)
                .where(field.in(values))
                .fetch()
                .into(type);
    }

    @Override
    public final <Z> P fetchOne(Field<Z> field, Z value) {
        R record = dsl
                .selectFrom(table)
                .where(field.equal(value))
                .fetchOne();

        return record == null ? null : record.into(type);
    }

    @Override
    public final Table<R> getTable() {
        return table;
    }

    @Override
    public final Class<P> getType() {
        return type;
    }

    protected abstract T getId(P object);

    @SuppressWarnings("unchecked")
    protected final T compositeKeyRecord(Object... values) {
        UniqueKey<R> key = table.getPrimaryKey();
        if (key == null)
            return null;

        TableField<R, Object>[] fields = (TableField<R, Object>[]) key.getFieldsArray();
        Record result = dsl
                .newRecord(fields);

        for (int i = 0; i < values.length; i++) {
            result.setValue(fields[i], fields[i].getDataType().convert(values[i]));
        }

        return (T) result;
    }

    @SuppressWarnings("unchecked")
    private final Condition equal(Field<?>[] pk, T id) {
        if (pk.length == 1) {
            return ((Field<Object>) pk[0]).equal(pk[0].getDataType().convert(id));
        } else {
            return row(pk).equal((Record) id);
        }
    }

    @SuppressWarnings("unchecked")
    private final Condition equal(Field<?>[] pk, Collection<T> ids) {
        if (pk.length == 1) {
            if (ids.size() == 1) {
                return equal(pk, ids.iterator().next());
            } else {
                return ((Field<Object>) pk[0]).in(pk[0].getDataType().convert(ids));
            }
        } else {
            return row(pk).in(ids.toArray(new Record[ids.size()]));
        }
    }

    private final Field<?>[] pk() {
        UniqueKey<?> key = table.getPrimaryKey();
        return key == null ? null : key.getFieldsArray();
    }

    private final List<R> records(Collection<P> objects, boolean forUpdate) {
        List<R> result = new ArrayList<R>();
        Field<?>[] pk = pk();

        for (P object : objects) {
            R record = dsl.newRecord(table, object);

            if (forUpdate && pk != null)
                for (Field<?> field : pk)
                    record.changed(field, false);

            this.resetChangedOnNotNull(record);
            result.add(record);
        }

        return result;
    }

    private void resetChangedOnNotNull(Record record) {
        int size = record.size();

        for (int i = 0; i < size; i++)
            if (record.getValue(i) == null)
                if (!record.field(i).getDataType().nullable())
                    record.changed(i, false);
    }

    // 정렬

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