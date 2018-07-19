package generation_controls;

import board.Board;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GenerationTest {

    private Generation testGeneration;
    private Board testBoard;

    @Before
    public void setUp() throws Exception {
        this.testGeneration = new Generation();
        this.testBoard = new Board(40, 40);
    }

    @Test
    public void pushBoard() {
        testGeneration.pushBoard(testBoard);
        assertEquals(1, testGeneration.getGeneratedBoards().size());
        assertEquals(testBoard, testGeneration.popBoard());
    }

    @Test
    public void popBoard() {
        testGeneration.pushBoard(testBoard);
        assertEquals(testBoard, testGeneration.popBoard());
    }


    @Test
    public void popBoardFromEmptyGeneration() {
        assertNull(testGeneration.popBoard());
    }

    @Test
    public void getFirst() {
        Board anotherTestBoard = new Board(20, 20);
        testGeneration.pushBoard(anotherTestBoard);
        testGeneration.pushBoard(testBoard);
        assertEquals(anotherTestBoard, testGeneration.getFirst());
    }
}