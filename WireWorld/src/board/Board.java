package board;

import generation_controls.FileIO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Board {
    private static boolean debugging = false;
    private Cell boardCells[][];
    private List<Structure> boardStructures = new ArrayList<>();
    private int boardWidth;


    private int boardHeight;

    public Board(int width, int height) {
        this.boardWidth = width;
        this.boardHeight = height;
        this.boardCells = new Cell[boardWidth][boardHeight];
        fillBoardWithEmptyCells();
    }

    public static void main(String args[]) throws IOException {
        //Board tempBoard = FileIO.readBoardFromFile("board1.txt");
        //  Generator generator = new Generator(tempBoard);
        // tempBoard = generator.runGeneration();
        //  System.out.println(generator.toString());

        //Board tempBoard = new Board(10, 10);
        //tempBoard.setCell(2, 4, "HEAD");
        //tempBoard.addStructure(6, 5, "DIODE", "U");
      //  System.out.print(tempBoard);
        //tempBoard.setCell(6, 5, "HEAD");
        //System.out.println(tempBoard.getBoardStructures());
        //FileIO.saveBoardToFile(tempBoard, "board1.txt");
    }

    public void setCell(int posX, int posY, String cellName, Structure structure) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        Structure parentStruct = boardCells[posX][posY].parentStructure;
        if (parentStruct != null) {
            deleteStructureFromBoard(parentStruct);
            boardStructures.remove(parentStruct);
        }
        Cell cellToSet = CellFactory.getCell(cellName, structure);

        this.boardCells[posX][posY] = cellToSet;

        if (debugging)
            System.out.println("W miejsce x: " + posX + ", y: " + posY + " wstawiono komórkę " + cellToSet);
    }

    public void setCell(int posX, int posY, String cellName) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        setCell(posX, posY, cellName, null);
    }

    public Cell getCell(int posX, int posY) throws ArrayIndexOutOfBoundsException {
        return this.boardCells[posX][posY];
    }

    public void addStructure(int posX, int posY, String structureName, String orientation) throws IllegalArgumentException {
        Orientation structureOrientation = getOrientationFromString(orientation);
        Structure structureToAdd = StructureFactory.getStructure(structureName, structureOrientation, posX, posY);
        boardStructures.add(structureToAdd);
        applyStructureOnBoard(structureToAdd);

        if (debugging)
            System.out.println("W miejsce x: " + posX + " y: " + posY + " wstawiono strukturę " + structureToAdd);
    }


    private Orientation getOrientationFromString(String orientationString) throws IllegalArgumentException {
        try {
            return Orientation.valueOf(orientationString);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid orientation");
        }
    }

    private void applyStructureOnBoard(Structure structure) {
        for (Coordinates cord : structure.getPattern()) {
            int newX = structure.getPosX() + cord.getX();
            int newY = structure.getPosY() + cord.getY();
            if ((newX < boardWidth && newX > 0) &&
                    (newY < boardHeight && newY > 0))
                setCell(newX, newY, "CONDUCTOR", structure);
        }
    }

    private void deleteStructureFromBoard(Structure structure) {
        for (Coordinates cord : structure.getPattern()) {
            int newX = structure.getPosX() + cord.getX();
            int newY = structure.getPosY() + cord.getY();
            if ((newX < boardWidth && newX > 0) &&
                    (newY < boardHeight && newY > 0))
                getCell(newX, newY).setParentStructure(null);
        }
    }

    public List<Structure> getBoardStructures() {
        return boardStructures;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    private void fillBoardWithEmptyCells() {
        for (Cell[] row : boardCells
                ) {
            Arrays.fill(row, new EmptyCell(null));
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                buffer.append(boardCells[x][y].toString().charAt(0));
                buffer.append(' ');
            }
            buffer.append('\n');
        }
        return buffer.toString();
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }
}
