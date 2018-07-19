package board;

import java.util.ArrayList;

public class Diode extends Structure {

    Diode(Orientation orientation, int posX, int posY) {
        super("Diode", orientation, posX, posY);

    }


    @Override
    protected ArrayList<Coordinates> takePattern() {
        ArrayList<Coordinates> pattern = new ArrayList<Coordinates>() {{
            add(new Coordinates(0, 0));
            add(new Coordinates(1, 0));
            add(new Coordinates(1, 1));
            add(new Coordinates(1, -1));
            add(new Coordinates(2, 1));
            add(new Coordinates(2, -1));
            add(new Coordinates(3, 0));
        }};
        return pattern;
    }
}
