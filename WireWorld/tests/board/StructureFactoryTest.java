package board;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StructureFactoryTest {

    @Test
    public void getStructure() {
        Structure testStructure = StructureFactory.getStructure("Diode", Orientation.L, 1, 1);
        assertEquals("Diode", testStructure.getStructName());
        assertEquals(Orientation.L, testStructure.getOrientation());
        assertEquals(1, testStructure.getPosX());
        assertEquals(1, testStructure.getPosY());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getIllegalStructure() {
        Structure testStructure = StructureFactory.getStructure("XD", Orientation.L, 1, 1);
    }

}