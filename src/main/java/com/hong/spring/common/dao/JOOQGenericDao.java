package com.hong.spring.common.dao;

import static org.jooq.impl.DSL.row;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.SortField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.UpdatableRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

@SuppressWarnings("rawtypes")
public abstract class JOOQGenericDao<R extends UpdatableRecord<R>, E, K> implements GenericDao<R, E, K> {

	private final Table<R> table;
	private final Class<E> type;

	@Autowired
	private DSLContext dsl;

	public DSLContext getDsl() {
		return dsl;
	}

	protected JOOQGenericDao(Table<R> table, Class<E> type) {
		this.table = table;
		this.type = type;
	}

	@Override
	public final void insert(Collection<E> objects) {

		if (objects.size() > 1) {
			dsl.batchInsert(records(objects, false)).execute();
		} else if (objects.size() == 1) {
			records(objects, false).get(0).insert();
		}
	}

	@Override
	public final void update(Collection<E> objects) {

		if (objects.size() > 1) {
			dsl.batchUpdate(records(objects, true)).execute();
		} else if (objects.size() == 1) {
			records(objects, true).get(0).update();
		}
	}

	@Override
	public final void delete(Collection<E> objects) {
		List<K> ids = new ArrayList<K>();

		for (E object : objects) {
			ids.add(getId(object));
		}

		deleteById(ids);
	}

	@Override
	public final void deleteById(Collection<K> ids) {
		Field<?>[] pk = pk();

		if (pk != null) {
			dsl.delete(table).where(equal(pk, ids)).execute();
		}
	}

	@Override
	public final boolean exists(E object) {
		return existsById(getId(object));
	}

	@Override
	public final boolean existsById(K id) {
		Field<?>[] pk = pk();

		if (pk != null) {
			return dsl.selectCount().from(table).where(equal(pk, id)).fetchOne(0, Integer.class) > 0;
		} else {
			return false;
		}
	}

	@Override
	public final long count() {
		return dsl.selectCount().from(table).fetchOne(0, Long.class);
	}

	@Override
	public final List<E> findAll() {
		return dsl.selectFrom(table).fetch().into(type);
	}

	@Override
	public final E findById(K id) {
		Field<?>[] pk = pk();
		R record = null;

		if (pk != null) {
			record = dsl.selectFrom(table).where(equal(pk, id)).fetchOne();
		}

		return record == null ? null : record.into(type);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final <Z> List<E> fetch(Field<Z> field, Z... values) {
		return dsl.selectFrom(table).where(field.in(values)).fetch().into(type);
	}

	@Override
	public final <Z> E fetchOne(Field<Z> field, Z value) {
		R record = dsl.selectFrom(table).where(field.equal(value)).fetchOne();

		return record == null ? null : record.into(type);
	}

	@Override
	public final Table<R> getTable() {
		return table;
	}

	@Override
	public final Class<E> getType() {
		return type;
	}

	/**
	 * FIXME : 상속받은 repository 에서 도메인 id 를 가져오도록 하는건 아름답지 못하다...
	 * 
	 * @param object
	 * @return
	 */
	protected abstract K getId(E object);

	@SuppressWarnings("unchecked")
	protected final K compositeKeyRecord(Object... values) {
		UniqueKey<R> key = table.getPrimaryKey();
		if (key == null)
			return null;

		TableField<R, Object>[] fields = (TableField<R, Object>[]) key.getFieldsArray();
		Record result = dsl.newRecord(fields);

		for (int i = 0; i < values.length; i++) {
			result.setValue(fields[i], fields[i].getDataType().convert(values[i]));
		}

		return (K) result;
	}

	@SuppressWarnings("unchecked")
	private final Condition equal(Field<?>[] pk, K id) {
		if (pk.length == 1) {
			return ((Field<Object>) pk[0]).equal(pk[0].getDataType().convert(id));
		} else {
			return row(pk).equal((Record) id);
		}
	}

	@SuppressWarnings("unchecked")
	private final Condition equal(Field<?>[] pk, Collection<K> ids) {
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

	private final List<R> records(Collection<E> objects, boolean forUpdate) {
		List<R> result = new ArrayList<R>();
		Field<?>[] pk = pk();

		for (E object : objects) {
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

	public Collection<SortField<?>> getSortFields(Sort sortSpecification) {
		Collection<SortField<?>> querySortFields = new ArrayList<>();

		if (sortSpecification == null) {
			return querySortFields;
		}
		
		sortSpecification.forEach(specifiedField -> {
			String sortFieldName = specifiedField.getProperty();
			Sort.Direction sortDirection = specifiedField.getDirection();

			TableField tableField = (TableField) this.getTable().field(sortFieldName);
			SortField<?> querySortField = convertTableFieldToSortField(tableField, sortDirection);
			querySortFields.add(querySortField);
		});

		return querySortFields;
	}

	private SortField<?> convertTableFieldToSortField(TableField tableField, Sort.Direction sortDirection) {
		if (sortDirection == Sort.Direction.ASC) {
			return tableField.asc();
		} else {
			return tableField.desc();
		}
	}

}