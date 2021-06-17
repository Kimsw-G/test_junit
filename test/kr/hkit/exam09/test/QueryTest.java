package kr.hkit.exam09.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QueryTest {

    private static String A_TITLE = "A";
    private static String B_TITLE = "B";
    private static String C_TITLE = "C";
    private static String D_TITLE = "D";
    private static String A_CONTENT = "A content";
    private static String B_CONTENT = "B content";
    private static String C_CONTENT = "C content";
    private static String D_CONTENT = "D content";


    @BeforeAll
    public static void beforeALl(){
        Query.createTable();
    }
    @AfterAll
    public static void afterAll(){
        Query.dropTable();
    }

    @BeforeEach
    public void beforeEach(){
        Query.boardDelete(0); // 0 => 모두 삭제
        // A객체 생성
        BoardVO voA = new BoardVO();
        voA.setBtitle(A_TITLE);
        voA.setBcontent(A_CONTENT);
        // B객체 생성
        BoardVO voB = new BoardVO();
        voB.setBtitle(B_TITLE);
        voB.setBcontent(B_CONTENT);
        // 쿼리문 실행
        Query.boardInsert(voA);
        Query.boardInsert(voB);
    }

    @Test
    public void testA(){
        // 값이 2개인지 확인!
        assertEquals(Query.getAllBoardList().size(),2);

        BoardVO boardVO;

        // A와 비교
        boardVO = Query.getBoardDetail(1);
        assertEquals(boardVO.getBid(),1);
        assertEquals(boardVO.getBtitle(),A_TITLE);
        assertEquals(boardVO.getBcontent(),A_CONTENT);
        // B와 비교
        boardVO = Query.getBoardDetail(2);
        assertEquals(boardVO.getBid(),2);
        assertEquals(boardVO.getBtitle(),B_TITLE);
        assertEquals(boardVO.getBcontent(),B_CONTENT);

        List<BoardVO> list = Query.getAllBoardList();
        assertEquals(list.get(0).getBid(),1);
        assertEquals(list.get(0).getBtitle(),A_TITLE);
        assertEquals(list.get(0).getBcontent(),A_CONTENT);
        assertEquals(list.get(1).getBid(),2);
        assertEquals(list.get(1).getBtitle(),B_TITLE);
        assertEquals(list.get(1).getBcontent(),B_CONTENT);
    }

    @Test
    public void testB(){
        // 1번 삭제 시도
        Query.boardDelete(1);
        BoardVO boardVO = Query.getBoardDetail(1);
        // 지워져서
        assertNotEquals(boardVO.getBid(),1);
        assertNotEquals(boardVO.getBtitle(),A_TITLE);
        assertNotEquals(boardVO.getBcontent(),A_CONTENT);
        // 값이 1개인지 확인!
        assertEquals(Query.getAllBoardList().size(),1);

        // 2번 삭제 시도
        Query.boardDelete(2);
        boardVO = Query.getBoardDetail(2);
        // 지워져서
        assertNotEquals(boardVO.getBid(),2);
        assertNotEquals(boardVO.getBtitle(),B_TITLE);
        assertNotEquals(boardVO.getBcontent(),B_CONTENT);
        // 값이 0개인지 확인!
        assertEquals(Query.getAllBoardList().size(),0);
    }

    @Test
    public void testC(){
        BoardVO voC = new BoardVO();
        voC.setBid(1);
        voC.setBtitle(C_TITLE);
        voC.setBcontent(C_CONTENT);
        // B객체 생성
        BoardVO voD = new BoardVO();
        voD.setBid(2);
        voD.setBtitle(D_TITLE);
        voD.setBcontent(D_CONTENT);

        Query.boardUpdate(voC);
        Query.boardUpdate(voD);

        List<BoardVO> list = Query.getAllBoardList();

        assertEquals(list.get(0).getBid(),1);
        assertEquals(list.get(0).getBtitle(),C_TITLE);
        assertEquals(list.get(0).getBcontent(),C_CONTENT);
        assertEquals(list.get(1).getBid(),2);
        assertEquals(list.get(1).getBtitle(),D_TITLE);
        assertEquals(list.get(1).getBcontent(),D_CONTENT);
    }
}
