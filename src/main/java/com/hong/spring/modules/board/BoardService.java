package com.hong.spring.modules.board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hong.spring.common.service.GenericServiceImpl;
import com.hong.spring.domains.Board;
import com.hong.spring.modules.board.support.BoardSearchContext;

@Service
public class BoardService extends GenericServiceImpl<BoardRepository, Board, Long> {

	@Transactional(readOnly = true)
	public Page<Board> getListByContext(BoardSearchContext searchContext) {
		long totalCount = getDao().getTotalCountByContext(searchContext);
		List<Board> boardList = getDao().getListByContext(searchContext);
		return new PageImpl<>(boardList, searchContext.getPageRequest(), totalCount);
	}

}
