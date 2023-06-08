package com.sy.personal_project.service;

import com.sy.personal_project.entity.Board;
import com.sy.personal_project.entity.BoardComment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TestServiceTest {

    @Autowired
    TestService testService;

    @Test
    void boardLoad() {
        /*Board load = testService.boardLoad();
        assertEquals("내용", load.getContent());
        List<BoardComment> comments = load.getBoardComments();
        assertEquals(2,comments.size());*/
    }
}