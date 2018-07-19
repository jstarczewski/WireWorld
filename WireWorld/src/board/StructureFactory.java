package board;

class StructureFactory {

    static Structure getStructure(String structureName, Orientation orientation, int posX, int posY) throws IllegalArgumentException {
        if (structureName.equalsIgnoreCase("CLOCK"))
            return new Clock(orientation, posX, posY);
        else if (structureName.equalsIgnoreCase("DIODE"))
            return new Diode(orientation, posX, posY);
        else if (structureName.equalsIgnoreCase("OR"))
            return new Or(orientation, posX, posY);
        else if (structureName.equalsIgnoreCase("XOR"))
            return new Xor(orientation, posX, posY);
        else if (structureName.equalsIgnoreCase("AND"))
            return new And(orientation, posX, posY);
        else
            throw new IllegalArgumentException("Invalid structure name");
    }
}
