package board;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    private Board testBoard;
    private Structure testStructure;

    @Before
    public void setUp() throws Exception {
        this.testBoard = new Board(40, 40);
    }

    @Test
    public void testSetCell() {
        testBoard.setCell(1, 1, "Head");
        assertTrue(testBoard.getCell(1, 1).name.equalsIgnoreCase("Head"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCellException() {
        testBoard.setCell(2, 2, "XD");
    }

    @Test
    public void testSetCellWithStructure() {
        testBoard.setCell(3, 3, "Head", testStructure);
        assertEquals(testBoard.getCell(3, 3).parentStructure, testStructure);
    }

    @Test
    public void testAddStructure() {
        testBoard.addStructure(4, 4, "Diode", "R");
        assertEquals(1, testBoard.getBoardStructures().size());
        Structure insertedDiode = testBoard.getBoardStructures().get(0);
        assertEquals(testBoard.getCell(4, 4), new Conductor(insertedDiode));
        assertEquals(testBoard.getCell(5, 4), new Conductor(insertedDiode));
        assertEquals(testBoard.getCell(5, 5), new Conductor(insertedDiode));
        assertEquals(testBoard.getCell(5, 3), new Conductor(insertedDiode));
        assertEquals(testBoard.getCell(6, 5), new Conductor(insertedDiode));
        assertEquals(testBoard.getCell(6, 3), new Conductor(insertedDiode));
        assertEquals(testBoard.getCell(7, 4), new Conductor(insertedDiode));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddIllegalStructure() {
        testBoard.addStructure(4, 4, "XD", "R");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddIllegalStructureOrientation() {
        testBoard.addStructure(4, 4, "Diode", "S");
    }
}