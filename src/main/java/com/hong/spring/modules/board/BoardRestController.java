package com.hong.spring.modules.board;

import com.hong.spring.common.jqgrid.JqGrid;
import com.hong.spring.common.util.AjaxReturn;
import com.hong.spring.domains.Board;
import com.hong.spring.modules.board.support.BoardSearchContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/board/")
public class BoardRestController {

    @Autowired
    private BoardService boardService;

    @RequestMapping("/list")
    public AjaxReturn list(BoardSearchContext searchContext) {
        List<Board> boards = boardService.getListByContext(searchContext);

        JqGrid<Board> jqGrid = new JqGrid<>();
        jqGrid.setPage(1);
        jqGrid.setRecords(10);
        jqGrid.setTotal(100);
        jqGrid.setRows(boards);

        return AjaxReturn.jsonWithSuccessResult().addAttribute("jqGrid", jqGrid);
    }

}