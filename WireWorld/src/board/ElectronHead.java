package board;

public class ElectronHead extends Cell {
    ElectronHead(Structure struct) {
        super(struct);
    }

    @Override
    protected void setProperties() {
        this.name = "Head";
        this.isConductive = true;
    }
}
