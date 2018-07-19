package board;

public abstract class Cell {
    Structure parentStructure;
    boolean isConductive;
    String name;

    Cell(Structure ownedBy) {
        this.parentStructure = ownedBy;
        setProperties();
    }

    public Structure getParentStructure() {
        return this.parentStructure;
    }

    void setParentStructure(Structure parentStructure) {
        this.parentStructure = parentStructure;
    }

    @Override
    public String toString() {
        return this.name;
    }

    protected abstract void setProperties();

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Cell && ((Cell) o).name.equals(this.name) && ((Cell) o).parentStructure.equals(this.parentStructure);
    }
}
