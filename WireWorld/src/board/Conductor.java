package board;

public class Conductor extends Cell {
    Conductor(Structure struct) {
        super(struct);
    }

    @Override
    protected void setProperties() {
        this.name = "Conductor";
        this.isConductive = true;
    }
}
