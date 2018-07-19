package board;

import java.util.ArrayList;

public class Clock extends Structure {
    Clock(Orientation orientation, int posX, int posY) {
        super("Clock", orientation, posX, posY);

    }

    @Override
    protected ArrayList<Coordinates> takePattern() {
        ArrayList<Coordinates> pattern = new ArrayList<Coordinates>() {{
            add(new Coordinates(0, 0));
            add(new Coordinates(1, 1));
            add(new Coordinates(2, 1));
            add(new Coordinates(3, 1));
            add(new Coordinates(4, 1));
            add(new Coordinates(5, 1));
            add(new Coordinates(6, 1));
            add(new Coordinates(7, 1));
            add(new Coordinates(8, 1));
            add(new Coordinates(9, 0));
            add(new Coordinates(8, -1));
            add(new Coordinates(7, -1));
            add(new Coordinates(6, -1));
            add(new Coordinates(5, -1));
            add(new Coordinates(4, -1));
            add(new Coordinates(3, -1));
            add(new Coordinates(2, -1));
            add(new Coordinates(1, -1));
        }};

        return pattern;
    }
}
