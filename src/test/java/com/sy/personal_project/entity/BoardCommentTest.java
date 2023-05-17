package com.sy.personal_project.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sy.personal_project.repository.BoardCommentRepository;
import com.sy.personal_project.repository.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardCommentTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardCommentRepository boardCommentRepository;

    @Autowired
    private EntityManager entityManager;

    private JPAQueryFactory queryFactory;

    @Test
    public void testCRUDOperations() {
        // Create
        Board board = new Board();
        board.setTitle("Sample Board");
        board.setContent("Sample Content");
        boardRepository.save(board);
        //Board tSavedBoard = boardRepository.save(board);

        BoardComment comment1 = new BoardComment();
        comment1.setContent("BoardComment 1");
        comment1.setBoard(board);
        boardCommentRepository.save(comment1);

        BoardComment comment2 = new BoardComment();
        comment2.setContent("BoardComment 2");
        comment2.setBoard(board);
        boardCommentRepository.save(comment2);

        /*List<BoardComment> s = new ArrayList<>();
        s.add(comment1);
        s.add(comment2);
        board.setBoardComments(s);
        Board tSavedBoard = boardRepository.save(board);
        List<BoardComment> tBoardComments = tSavedBoard.getBoardComments();*/

        // Read
        Optional<Board> savedBoardOptional = boardRepository.findById(board.getId());
        assertTrue(savedBoardOptional.isPresent());
        Board savedBoard = savedBoardOptional.get();
        assertEquals("Sample Board", savedBoard.getTitle());
        assertEquals("Sample Content", savedBoard.getContent());

        List<BoardComment> boardComments = savedBoard.getBoardComments();
        Integer testSize = boardComments.size();
        List<BoardComment> boardCommentLoadTest = boardCommentRepository.findAllByBoard(board);

        assertEquals(2, boardComments.size());

        // Update
        savedBoard.setTitle("Updated Board");
        savedBoard.setContent("Updated Content");
        boardRepository.save(savedBoard);

        Optional<BoardComment> savedCommentOptional = boardCommentRepository.findById(comment1.getId());
        assertTrue(savedCommentOptional.isPresent());
        BoardComment savedBoardComments = savedCommentOptional.get();
        savedBoardComments.setContent("Updated BoardComment 1");
        boardCommentRepository.save(savedBoardComments);

        // Delete
        boardRepository.delete(savedBoard);
        assertFalse(boardRepository.existsById(savedBoard.getId()));
        assertFalse(boardCommentRepository.existsById(comment1.getId()));
        assertFalse(boardCommentRepository.existsById(comment2.getId()));
    }

    @Test
    public void boardAndBoardCommentSaveTest() {
        Optional<Board> loadOptional = boardRepository.findById(1L);
        assertTrue(loadOptional.isPresent());
        Board load = loadOptional.get();

        assertEquals("내용", load.getContent());
        load.delete();
        assertEquals(true,load.getDelAt());

        List<BoardComment> boardComments = load.getBoardComments();

        for (BoardComment comment : boardComments) {
            assertEquals(true, comment.getDelAt());
        }
    }


}