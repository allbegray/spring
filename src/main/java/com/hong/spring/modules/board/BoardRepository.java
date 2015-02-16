package com.hong.spring.modules.board;

import com.hong.spring.domains.Board;
import com.hong.spring.modules.board.support.BoardSearchContext;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.SortField;
import org.jooq.TableField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.jooq.util.maven.example.tables.Board.BOARD;

@Repository
public class BoardRepository {

    @Autowired
    private DSLContext dsl;

    public long getTotalCountByContext(BoardSearchContext searchContext) {
        return dsl.selectCount().from(BOARD)
                .where(this.createWhereConditions(searchContext))
                .fetchOne(0, long.class);
    }

    public List<Board> getListByContext(BoardSearchContext searchContext) {
        Pageable pageable = searchContext.getPageRequest();

        return dsl.selectFrom(BOARD)
                .where(this.createWhereConditions(searchContext))
                .orderBy(this.getSortFields(pageable.getSort()))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch()
                .into(Board.class);
    }

    private Collection<SortField<?>> getSortFields(Sort sortSpecification) {
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
            Field tableField = BOARD.getClass().getField(sortFieldName);
            sortField = (TableField) tableField.get(BOARD);
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

    private Condition createWhereConditions(BoardSearchContext searchContext) {
        Condition where = null;
        if (StringUtils.hasText(searchContext.getKeyword())) {
            if ("title".equals(searchContext.getSelect_type())) {
                where = BOARD.TITLE.likeIgnoreCase("%" + searchContext.getKeyword() + "%");
            } else if ("desc".equals(searchContext.getSelect_type())) {
                where = BOARD.TITLE.likeIgnoreCase("%" + searchContext.getKeyword() + "%");
            }
        }
        return where;
    }

}
