package board;

class CellFactory {

    static Cell getCell(String cellName, Structure structure) throws IllegalArgumentException {

        if (cellName.equalsIgnoreCase("EMPTY"))
            return new EmptyCell(structure);
        else if (cellName.equalsIgnoreCase("CONDUCTOR"))
            return new Conductor(structure);
        else if (cellName.equalsIgnoreCase("HEAD"))
            return new ElectronHead(structure);
        else if (cellName.equalsIgnoreCase("TAIL"))
            return new ElectronTail(structure);
        else
            throw new IllegalArgumentException("Invalid cell name");
    }

}
