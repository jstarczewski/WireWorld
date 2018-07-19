package board;

import java.util.ArrayList;

public class Or extends Structure {

    Or(Orientation orientation, int posX, int posY) {
        super("Or", orientation, posX, posY);
    }


    @Override
    protected ArrayList<Coordinates> takePattern() {
        ArrayList<Coordinates> pattern = new ArrayList<Coordinates>() {{
            add(new Coordinates(0, 0));
            add(new Coordinates(0, 2));
            add(new Coordinates(1, -1));
            add(new Coordinates(1, 3));
            add(new Coordinates(2, -1));
            add(new Coordinates(2, 1));
            add(new Coordinates(2, 3));
            add(new Coordinates(3, 0));
            add(new Coordinates(3, 2));
            add(new Coordinates(3, 1));
            add(new Coordinates(4, 1));
        }};
        return pattern;
    }
}
