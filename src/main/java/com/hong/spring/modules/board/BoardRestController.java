package com.hong.spring.modules.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hong.spring.common.util.AjaxReturn;
import com.hong.spring.domains.Board;
import com.hong.spring.modules.board.support.BoardSearchContext;

@RestController
@RequestMapping("/api/board/")
public class BoardRestController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/tx")
	public AjaxReturn tx() {
		boardService.testTx();
		return AjaxReturn.jsonWithSuccessResult();
	}
	
	@RequestMapping("/add")
	public AjaxReturn add(Board board) {
		boardService.insert(board);
		return AjaxReturn.jsonWithSuccessResult();
	}

	@RequestMapping("/{id}")
	public AjaxReturn get(@PathVariable("id") int id) {
		Board board = boardService.findById(id);
		return AjaxReturn.jsonWithSuccessResult().addAttribute("board", board);
	}

	@RequestMapping("/list")
	public AjaxReturn list(BoardSearchContext searchContext) {
		Page<Board> boards = boardService.getListByContext(searchContext);
		return AjaxReturn.jsonWithJqGridAndSuccessResult(boards);
	}

}