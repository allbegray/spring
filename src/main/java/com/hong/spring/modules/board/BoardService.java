package com.hong.spring.modules.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hong.spring.domains.Board;
import com.hong.spring.modules.board.support.BoardSearchContext;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public void insert(Board board) {
		boardRepository.insert(board);
	}

	@Transactional(readOnly = true)
	public Board findById(long id) {
		return boardRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Page<Board> getListByContext(BoardSearchContext searchContext) {
		long totalCount = boardRepository.getTotalCountByContext(searchContext);
		List<Board> boardList = boardRepository.getListByContext(searchContext);
		return new PageImpl<>(boardList, searchContext.getPageRequest(), totalCount);
	}

}
