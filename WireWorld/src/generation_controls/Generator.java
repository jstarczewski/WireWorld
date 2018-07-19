package generation_controls;

import board.Board;
import board.Cell;

public class Generator {



    public Board runGeneration(Board startingBoard, int start) {
     //   int x;
   //     for (x = 0; x < start; x++) {
        //    generation.pushBoard(startingBoard);
       // }

        return processBoard(startingBoard);
    }

    private Board processBoard(Board board) {
        Board tempBoard = new Board(board.getBoardWidth(), board.getBoardHeight());
        for (int x = 0; x < board.getBoardWidth(); x++) {
            for (int y = 0; y < board.getBoardHeight(); y++) {
                Cell cell = board.getCell(x, y);
                switch (cell.getName()) {
                    case "Empty": {
                        break;
                    }
                    case "Head": {
                        tempBoard.setCell(x, y, "Tail");
                        break;
                    }
                    case "Conductor": {
                        tempBoard.setCell(x, y, checkNeighbours(board, x, y));
                        break;
                    }
                    case "Tail": {
                        tempBoard.setCell(x, y, "Conductor");
                        break;
                    }
                }
            }
        }
        return tempBoard;
    }

    private static String checkNeighbours(Board startingBoard, int x, int y) {
        int i = 0;
        String state = "Head";

        if (x - 1 >= 0) {
            if (startingBoard.getCell(x - 1, y).getName().equals(state)) {
                i++;
            }
            if (y - 1 >= 0) {
                if (startingBoard.getCell(x - 1, y - 1).getName().equals(state)) {
                    i++;
                }

                if (startingBoard.getCell(x, y - 1).getName().equals(state)) {
                    i++;

                }
                if (x + 1 < startingBoard.getBoardWidth()) {
                    if (startingBoard.getCell(x + 1, y - 1).getName().equals(state)) {
                        i++;
                    }
                }
            }
            if (y + 1 < startingBoard.getBoardHeight())
                if (startingBoard.getCell(x - 1, y + 1).getName().equals(state)) {
                    i++;
                }
        }
        if (y + 1 < startingBoard.getBoardHeight()) {
            if (startingBoard.getCell(x, y + 1).getName().equals(state)) {
                i++;
            }
            if (x + 1 < startingBoard.getBoardWidth()) {
                if (startingBoard.getCell(x + 1, y + 1).getName().equals(state)) {
                    i++;
                }
                if (startingBoard.getCell(x + 1, y).getName().equals(state)) {
                    i++;
                }
            }
        }


        if (i == 1 || i == 2) {
            return "Head";
        } else {
            return "Conductor";
        }

    }

}
