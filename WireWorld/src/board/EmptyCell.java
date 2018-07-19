package board;

public class EmptyCell extends Cell {


    EmptyCell(Structure struct) {
        super(struct);
    }

    @Override
    protected void setProperties() {
        this.name = "Empty";
        this.isConductive = false;
    }
}
