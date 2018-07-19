package board;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoordinatesTest {

    private Coordinates testCoordinates;

    @Before
    public void setUp() throws Exception {
        this.testCoordinates = new Coordinates(2, 4);
    }

    @Test
    public void applyOrientation() {
        testCoordinates.applyOrientation(Orientation.R);
        assertEquals(2, testCoordinates.getX());
        assertEquals(4, testCoordinates.getY());
        testCoordinates.applyOrientation(Orientation.L);
        assertEquals(-2, testCoordinates.getX());
        assertEquals(4, testCoordinates.getY());
        testCoordinates.applyOrientation(Orientation.U);
        assertEquals(-4, testCoordinates.getX());
        assertEquals(-2, testCoordinates.getY());
        testCoordinates.applyOrientation(Orientation.D);
        assertEquals(-2, testCoordinates.getX());
        assertEquals(4, testCoordinates.getY());
    }
}