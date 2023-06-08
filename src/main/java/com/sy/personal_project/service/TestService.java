package com.sy.personal_project.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sy.personal_project.entity.Board;
import com.sy.personal_project.entity.QBoard;
import com.sy.personal_project.entity.QBoardComment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TestService {
    private final JPAQueryFactory queryFactory;


    public Board boardLoad() {
        QBoard qBoard = QBoard.board;
        QBoardComment qBoardComment = QBoardComment.boardComment;
        Board load = queryFactory
                .selectFrom(qBoard)
                .leftJoin(qBoard.boardComments, qBoardComment).where(qBoardComment.delAt.isFalse()).fetchJoin() //n+1문제를 해결하기위해 작성해준다 이부분을 생략했을경우 boardComment가 사용될때 select쿼리가 한번더 실행되기 때문에 이를 줄여줄 수 있는방법이다.
                .where(qBoard.id.eq(1L))
                .fetchOne();
        log.debug("Log Test");
        return load;
    }
}
