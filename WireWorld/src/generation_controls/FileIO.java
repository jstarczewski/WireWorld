package generation_controls;

import board.Board;
import board.Cell;
import board.EmptyCell;
import board.Structure;
import javafx.scene.control.Label;
import javafx.util.Pair;

import java.io.*;
import java.util.List;

public abstract class FileIO {

    public static void saveBoardToFile(Board board, String pathToFile) throws IOException {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(pathToFile);
        } catch (IOException e) {
            throw new IOException("Couldn't create output file");
        }
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        writeBoardDimensions(board, bufferedWriter);
        writeBoardCells(board, bufferedWriter);
        writeBoardStructures(board, bufferedWriter);

        bufferedWriter.close();
        fileWriter.close();

    }

    private static void writeBoardDimensions(Board board, BufferedWriter bufferedWriter) throws IOException {
        int boardWidth = board.getBoardWidth();
        int boardHeight = board.getBoardHeight();

        bufferedWriter.write(String.valueOf(boardWidth));
        bufferedWriter.write(" ");
        bufferedWriter.write(String.valueOf(boardHeight));
        bufferedWriter.newLine();
    }

    private static void writeBoardCells(Board board, BufferedWriter bufferedWriter) throws IOException {
        int boardWidth = board.getBoardWidth();
        int boardHeight = board.getBoardHeight();

        for (int x = 0; x < boardWidth; x++)
            for (int y = 0; y < boardHeight; y++) {
                Cell currentCell = board.getCell(x, y);
                if (currentCell.getParentStructure() == null && !(currentCell instanceof EmptyCell)) {
                    bufferedWriter.write(board.getCell(x, y).toString());
                    bufferedWriter.write(" ");
                    bufferedWriter.write(String.valueOf(x));
                    bufferedWriter.write(" ");
                    bufferedWriter.write(String.valueOf(y));
                    bufferedWriter.newLine();
                }
            }
    }

    private static void writeBoardStructures(Board board, BufferedWriter bufferedWriter) throws IOException {
        List<Structure> structureList = board.getBoardStructures();
        for (Structure structure : structureList) {
            bufferedWriter.write(structure.toString());
            bufferedWriter.write(" ");
            bufferedWriter.write(String.valueOf(structure.getPosX()));
            bufferedWriter.write(" ");
            bufferedWriter.write(String.valueOf(structure.getPosY()));
            bufferedWriter.write(" ");
            bufferedWriter.write(String.valueOf(structure.getOrientation()));
            bufferedWriter.newLine();

        }
    }

    public static Board readBoardFromFile(File file, int width, int height, Label alertLabel) throws IOException, IllegalArgumentException, ArrayIndexOutOfBoundsException {
        Board board;
        if (file.isFile() && file.getName().endsWith(".txt")) {
            FileReader inputFile = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(inputFile);

            Pair boardDimensions = readBoardDimensions(bufferedReader, width, height, alertLabel);
            board = new Board((int) boardDimensions.getKey(), (int) boardDimensions.getValue());

            readBoardElements(board, bufferedReader);

            bufferedReader.close();
            inputFile.close();
        }
        else {
            alertLabel.setText("Only .txt files");
            board = new Board(width,height);
        }

        return board;
    }


    private static Pair<Integer, Integer> readBoardDimensions(BufferedReader bufferedReader, int width, int height, Label alertLabel)  {

        int boardWidth, boardHeight;
        try {
        String fileLine = bufferedReader.readLine();
        String[] dimensions = fileLine.split(" ");

            boardWidth = Integer.parseInt(dimensions[0]);
            boardHeight = Integer.parseInt(dimensions[1]);
        } catch (IllegalArgumentException | IOException | NullPointerException e) {
            boardWidth=width;
            boardHeight=height;
            alertLabel.setText("Problems with file");
           // throw new IllegalArgumentException("Invalid dimension values");
        }
        return new Pair<>(boardWidth, boardHeight);
    }

    private static void readBoardElements(Board board, BufferedReader bufferedReader) throws IOException {
        String fileLine;
        int posX, posY;

        while ((fileLine = bufferedReader.readLine()) != null) {
            String[] data = fileLine.split(" ");
            if (data.length < 3 || data.length > 4) {
                System.out.println("Passing invalid line in file, wrong format: " + fileLine);
                continue;
            }
            try {
                posX = Integer.parseInt(data[1]);
                posY = Integer.parseInt(data[2]);
            } catch (NumberFormatException e) {
                System.out.println("Passing invalid line in file, wrong element position: " + fileLine);
                continue;
            }

            addElement(board, posX, posY, data, fileLine);
        }
    }

    private static void addElement(Board board, int posX, int posY, String[] data, String fileLine) {
        try {
            if (data.length == 3)
                board.setCell(posX, posY, data[0]);
            else
                board.addStructure(posX, posY, data[0], data[3]);
        } catch (ArrayIndexOutOfBoundsException eIndex) {
            System.out.println("Passing invalid line in file, wrong element position: " + fileLine);
        } catch (IllegalArgumentException eArg) {
            if (eArg.getMessage().equals("Invalid orientation"))
                System.out.println("Passing invalid line in file, wrong orientation: " + fileLine);
            else
                System.out.println("Passing invalid line in file, wrong element: " + fileLine);
        }
    }

}
