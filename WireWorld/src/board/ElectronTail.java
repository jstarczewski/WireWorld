package board;

public class ElectronTail extends Cell {
    ElectronTail(Structure struct) {
        super(struct);
    }

    @Override
    protected void setProperties() {
        this.name = "Tail";
        this.isConductive = true;
    }
}
