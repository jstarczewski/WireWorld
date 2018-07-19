package board;

import java.util.ArrayList;

public class Xor extends Structure {
    Xor(Orientation orientation, int posX, int posY) {
        super("And", orientation, posX, posY);
    }

    @Override
    protected ArrayList<Coordinates> takePattern() {
        ArrayList<Coordinates> pattern = new ArrayList<Coordinates>() {{
            add(new Coordinates(0, 0));
            add(new Coordinates(0, -2));
            add(new Coordinates(1, 1));
            add(new Coordinates(1, -3));
            add(new Coordinates(2, 2));
            add(new Coordinates(2, -4));
            add(new Coordinates(3, 2));
            add(new Coordinates(3, 0));
            add(new Coordinates(3, -1));
            add(new Coordinates(3, -2));
            add(new Coordinates(3, -4));
            add(new Coordinates(4, 1));
            add(new Coordinates(4, 0));
            add(new Coordinates(4, -2));
            add(new Coordinates(4, -3));
            add(new Coordinates(5, 0));
            add(new Coordinates(5, -1));
            add(new Coordinates(5, -2));
            add(new Coordinates(6, 0));
            add(new Coordinates(6, -1));
            add(new Coordinates(6, -2));
            add(new Coordinates(7, -1));
        }};
        return pattern;
    }
}
