package com.hong.spring.modules.board;

import com.hong.spring.domains.Board;
import com.hong.spring.modules.board.support.BoardSearchContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BoardService {

    @Transactional(readOnly = true)
    public List<Board> getListByContext(BoardSearchContext searchContext) {
        List<Board> boardList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            boardList.add(new Board(i + 1, "제목" + i, "내용" + i, new Date()));
        }
        return boardList;
    }

}
