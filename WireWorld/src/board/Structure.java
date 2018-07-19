package board;

import java.util.List;

enum Orientation {
    R, U, L, D //R- Right, U- Up, L- Left, D- Down
}

public abstract class Structure {
    private String structName;

    private List<Coordinates> structPattern;
    private Orientation structOrientation;

    private int posX;
    private int posY;

    Structure(String name, Orientation orientation, int posX, int posY) {
        this.structName = name;
        this.posX = posX;
        this.posY = posY;
        this.structOrientation = orientation;
        this.structPattern = takePattern();
        applyOrientation();
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    private void applyOrientation() {
        for (Coordinates cord : this.structPattern
                ) {
            cord.applyOrientation(this.getOrientation());
        }
    }

    protected abstract List<Coordinates> takePattern();

    List<Coordinates> getPattern() {
        return this.structPattern;
    }

    public Orientation getOrientation() {
        return structOrientation;
    }

    @Override
    public String toString() {
        return structName;
    }

    public String getStructName() {
        return structName;
    }

    public void setStructName(String structName) {
        this.structName = structName;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Structure) {
            Structure structure = (Structure) o;
            return structure.posY == this.posY && structure.posX == this.posX
                    && structure.getOrientation() == this.getOrientation() && structure.structName.equals(this.structName);
        } else return false;
    }
}