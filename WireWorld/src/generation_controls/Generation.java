package generation_controls;

import board.Board;

import java.util.ArrayList;
import java.util.List;

public class Generation {


    private List<Board> generatedBoards;
    private int currentGenNumber;
    private int i = 0;

    public Generation() {
        this.generatedBoards = new ArrayList<>();
        this.currentGenNumber = 0;

    }

/*
    public void setCurrentGen(int newGenNumber) throws ArrayIndexOutOfBoundsException {
        if (currentGenNumber > totalGenNumber)
            throw new ArrayIndexOutOfBoundsException("Illegal generation number");
        else
            currentGenNumber = newGenNumber - 1;
    }*/

    public void pushBoard(Board board) {
        currentGenNumber++;
        if (i > generatedBoards.size())
            i--;
        generatedBoards.add(i, board);
        i++;
    }

    public Board popBoard() {
        if (currentGenNumber == 0) return null;

        if (i > 1) {
            return getGeneratedBoards().get(--i);
        }
        return getGeneratedBoards().get(0);
    }

    public Board getFirst() {
        return generatedBoards.get(0);
    }

    @Override
    public String toString() {
        return getGeneratedBoards().toString();
    }

    public int getCurrentGenNumber() {
        return currentGenNumber;
    }
    public List<Board> getGeneratedBoards() {
        return generatedBoards;
    }

}
