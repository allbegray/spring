package com.hong.spring.modules.board;

import com.hong.spring.common.dao.JOOQGenericDao;
import com.hong.spring.domains.Board;
import com.hong.spring.modules.board.support.BoardSearchContext;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.TableImpl;
import org.jooq.util.maven.example.tables.JBoard;
import org.jooq.util.maven.example.tables.records.JBoardRecord;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.jooq.util.maven.example.tables.JBoard.BOARD;

@Repository
public class BoardRepository extends JOOQGenericDao<JBoardRecord, Board, Long> {

    public BoardRepository() {
        super(JBoard.BOARD, Board.class);
    }

    @Override
    protected Long getId(Board object) {
        return object.getId();
    }

    public long getTotalCountByContext(BoardSearchContext searchContext) {
        return this.getDsl().selectCount().from(this.getTable())
                .where(this.createWhereConditions(searchContext))
                .fetchOne(0, long.class);
    }

    public List<Board> getListByContext(BoardSearchContext searchContext) {
        Pageable pageable = searchContext.getPageRequest();

        return this.getDsl().selectFrom(this.getTable())
                .where(this.createWhereConditions(searchContext))
                .orderBy(this.getSortFields(pageable.getSort()))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch()
                .into(Board.class);
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
