package com.hong.spring.modules.board;

import com.hong.spring.common.util.AjaxReturn;
import com.hong.spring.domains.Board;
import com.hong.spring.modules.board.support.BoardSearchContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board/")
public class BoardRestController {

    @Autowired
    private BoardService boardService;

    @RequestMapping("/{id}")
    public AjaxReturn get(@PathVariable("id") int id) {
        Board board = boardService.getById(id);
        return AjaxReturn.jsonWithSuccessResult().addAttribute("board", board);
    }

    @RequestMapping("/list")
    public AjaxReturn list(BoardSearchContext searchContext) {
        Page<Board> boards = boardService.getListByContext(searchContext);
        return AjaxReturn.jsonWithJqGridAndSuccessResult(boards);
    }

}