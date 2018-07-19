package GUI;

import board.Board;
import board.Cell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class GeneratorGUI {

    private GridPane board = new GridPane();
    private int width;
    private boolean isEditingEnabled = true;
    private int height;
    private int rectangleWidth;
    private int rectangleHeight;
    private Board playBoard;
    private Rectangle r;

    public Board getPlayBoard() {
        return playBoard;
    }

    public void setPlayBoard(Board playBoard) {
        this.playBoard = playBoard;
    }

    GeneratorGUI(int width, int height, int rectangleWidth, int rectangleHeight) {
        this.width = width;
        this.height = height;
        this.rectangleHeight = rectangleHeight;
        this.rectangleWidth = rectangleWidth;
    }

    void fillBoard(GridPane board) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {


                Rectangle rectangle = new Rectangle(0, 0, rectangleWidth, rectangleHeight);
                rectangle.setFill(Color.BLACK);

                GridPane.setConstraints(rectangle, x, y);
                board.getChildren().add(rectangle);
            }
        }
    }

    public void updateBoard() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                Rectangle r = (Rectangle) board.getChildren().get(y + (height * x));
                Cell cell = playBoard.getCell(x, y);
                if (cell.getName().equals("Head")) {
                    r.setFill(Color.BLUE);
                } else if (cell.getName().equals("Conductor")) {
                    r.setFill(Color.YELLOW);
                } else if (cell.getName().equals("Tail")) {
                    r.setFill(Color.RED);
                }
            }
        }
    }

    public void fillPlayBoard() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                Rectangle r = (Rectangle) board.getChildren().get(y + (height * x));
                if (r.getFill() == Color.YELLOW) {
                    playBoard.setCell(x, y, "Conductor");
                }
                if (r.getFill() == Color.BLUE) {
                    playBoard.setCell(x, y, "Head");
                }
                if (r.getFill() == Color.RED) {
                    playBoard.setCell(x, y, "Tail");
                }
                //if (r.getFill() == Color.BLACK) {
                //   playBoard.setCell(x,y, "Empty");
                //}

            }
        }
    }

    void detectOnMouseBehaviour(GridPane board) {


        board.setOnMouseClicked(event -> {
            if (isEditingEnabled) {
                int x = (int) (event.getX() / rectangleWidth);
                int y = (int) (event.getY() / rectangleHeight);

                MouseButton mouseButton = event.getButton();
                if (mouseButton == MouseButton.PRIMARY) {
                    if (x >= 0 && y >= 0) {
                        r = (Rectangle) board.getChildren().get(y + (height * x));
                        if (r.getFill() == Color.YELLOW) {
                            r.setFill(Color.BLACK);
                            playBoard.setCell(x, y, "Empty");
                        } else {
                            r.setFill(Color.YELLOW);
                        }
                    }
                } else if (mouseButton == MouseButton.SECONDARY) {
                    if (x >= 0 && y >= 0) {
                        r = (Rectangle) board.getChildren().get(y + (height * x));
                        if (r.getFill() == Color.YELLOW) {
                            r.setFill(Color.BLUE);
                        } else if (r.getFill() == Color.BLUE) {
                            r.setFill(Color.RED);
                        } else if (r.getFill() == Color.RED) {
                            r.setFill(Color.YELLOW);
                        }
                    }
                }
            }
        });


        board.setOnMouseDragged((MouseEvent e) -> {
            if (isEditingEnabled) {
                int x = (int) (e.getX() / rectangleWidth);
                int y = (int) (e.getY() / rectangleHeight);
                if (x >= 0 && x < width && y >= 0 && y < height) {
                    r = (Rectangle) board.getChildren().get(y + (height * x));
                    r.setFill(Color.YELLOW);

                }
            }
        });
    }

    public GridPane getBoard() {
        return board;
    }

    public void setBoard(GridPane board) {
        this.board = board;
    }

    public boolean isEditingEnabled() {
        return isEditingEnabled;
    }

    public void setEditingEnabled(boolean editingEnabled) {
        isEditingEnabled = editingEnabled;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


}
