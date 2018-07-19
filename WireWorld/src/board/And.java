package board;

import java.util.ArrayList;

public class And extends Structure {
    And(Orientation orientation, int posX, int posY) {
        super("And", orientation, posX, posY);
    }

    @Override
    protected ArrayList<Coordinates> takePattern() {
        ArrayList<Coordinates> pattern = new ArrayList<Coordinates>() {{
            add(new Coordinates(0, 0));
            add(new Coordinates(0, -3));
            add(new Coordinates(1, 0));
            add(new Coordinates(1, -4));
            add(new Coordinates(1, -5));
            add(new Coordinates(1, -6));
            add(new Coordinates(2, 0));
            add(new Coordinates(2, -7));
            add(new Coordinates(3, 0));
            add(new Coordinates(3, -6));
            add(new Coordinates(3, -5));
            add(new Coordinates(3, -4));
            add(new Coordinates(4, 0));
            add(new Coordinates(4, -3));
            add(new Coordinates(5, 0));
            add(new Coordinates(5, -2));
            add(new Coordinates(5, -3));
            add(new Coordinates(5, -4));
            add(new Coordinates(6, 0));
            add(new Coordinates(6, -3));
            add(new Coordinates(7, 0));
            add(new Coordinates(7, -2));
            add(new Coordinates(7, -4));
            add(new Coordinates(8, -1));
            add(new Coordinates(8, -5));
            add(new Coordinates(9, -1));
            add(new Coordinates(9, -5));
            add(new Coordinates(10, -1));
            add(new Coordinates(10, -4));
            add(new Coordinates(10, -5));
            add(new Coordinates(10, -6));
            add(new Coordinates(11, -1));
            add(new Coordinates(11, -5));
            add(new Coordinates(12, -2));
            add(new Coordinates(12, -3));
            add(new Coordinates(12, -4));
            add(new Coordinates(12, -6));
            add(new Coordinates(13, -6));
        }};
        return pattern;
    }
}
