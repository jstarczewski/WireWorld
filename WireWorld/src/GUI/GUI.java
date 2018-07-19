package GUI;

import board.Board;
import generation_controls.FileIO;
import generation_controls.Generation;
import generation_controls.Generator;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;


public class GUI extends Application {

    private Boolean isRunning = false;
    private int width = 100;
    private int height = 100;
    private Timer timer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane mainPane = new GridPane();
        GridPane leftPane = new GridPane();


        TextInputDialog dialog = new TextInputDialog("100x100");

        dialog.setHeaderText("Enter board width and height");
        dialog.setTitle("Enter board width and height");
        dialog.setContentText("Board size width x height (exm. 100x100)");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(data -> {
            try {
                width = Integer.parseInt(data.split("x")[0]);
                height = Integer.parseInt(data.split("x")[1]);
            } catch (IllegalArgumentException | NullPointerException e) {
                width = 100;
                height = 100;
            }
        });


        Board playBoard = new Board(width, height);
        GeneratorGUI generatorGUI = new GeneratorGUI(playBoard.getBoardWidth(), playBoard.getBoardHeight(), 10, 10);
        GridPane boardPane = generatorGUI.getBoard();


        generatorGUI.fillBoard(boardPane);
        generatorGUI.setPlayBoard(playBoard);
        generatorGUI.detectOnMouseBehaviour(boardPane);
        Generation generation = new Generation();
        Generator generator = new Generator();
        VisualisationControlGUI visualisationControlGUI = new VisualisationControlGUI();
        GridPane generationControlPane = visualisationControlGUI.getMainGenerationPane();

        FileIOGUI fileIOGUI = new FileIOGUI();
        GridPane fileIOControlPane = fileIOGUI.getFileIOPane();

        generatorGUI.setPlayBoard(playBoard);
        generatorGUI.updateBoard();

        visualisationControlGUI.getBtGenerate().setOnMouseClicked(e -> {
            if(!isRunning) {
                replaceGeneration(generator, generatorGUI, generation);
            }
        });

        visualisationControlGUI.getBtNext().setOnMouseClicked(e -> {
            if (!isRunning) {
                replaceGeneration(generator, generatorGUI, generation);
            }
        });
        visualisationControlGUI.getBtFirst().setOnMouseClicked(e -> {
            if (!isRunning) {
                if (generation.getCurrentGenNumber()!=0) {
                    generatorGUI.setPlayBoard(generation.getFirst());
                    generatorGUI.updateBoard();
                }
            }
        });
        visualisationControlGUI.getBtPrevious().setOnMouseClicked(e -> {
            if (!isRunning) {
                if (generation.getCurrentGenNumber()!=0) {
                    generatorGUI.setPlayBoard(generation.popBoard());
                    generatorGUI.updateBoard();
                }
            }
        });
        visualisationControlGUI.getBtPlay().setOnMouseClicked(e -> {


            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (isRunning) {
                        generatorGUI.setEditingEnabled(false);
                        replaceGeneration(generator, generatorGUI, generation);
                    } else {
                        replaceGeneration(generator, generatorGUI, generation);
                        generatorGUI.setEditingEnabled(true);
                        this.cancel();
                    }
                }
            };

            if (!isRunning) {
                isRunning = true;
                visualisationControlGUI.getBtPlay().setTextFill(Color.RED);
                timer.schedule(timerTask, 10, (long) fileIOGUI.getSlider().getValue());
                timerTask.run();
            } else {
                visualisationControlGUI.getBtPlay().setTextFill(Color.BLACK);
                isRunning = false;
            }


        });

        fileIOGUI.getBtSave().setOnMouseClicked(e -> {
            if (!isRunning) {
                try {
                    generatorGUI.fillPlayBoard();
                    FileIO.saveBoardToFile(generatorGUI.getPlayBoard(), "generation" + generation.getCurrentGenNumber() + ".txt");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }
        });

        fileIOGUI.getBtChoose().setOnMouseClicked(e -> {
            if (!isRunning) {
                Board board = readBoardFromFile(generatorGUI.getPlayBoard().getBoardWidth(), generatorGUI.getPlayBoard().getBoardHeight(), fileIOGUI.getLbCurrentFileName());
                    generatorGUI.setPlayBoard(board);
                    generatorGUI.updateBoard();
            }
        });


        GridPane.setConstraints(generationControlPane, 0, 0);
        leftPane.getChildren().add(generationControlPane);

        GridPane.setConstraints(fileIOControlPane, 0, 1);
        leftPane.getChildren().add(fileIOControlPane);

        leftPane.setPrefWidth(180);
        GridPane.setConstraints(leftPane, 0, 0);
        mainPane.getChildren().add(leftPane);


        ScrollPane scrollPane = new ScrollPane(boardPane);
        scrollPane.setMinHeight(400);
        scrollPane.setMinWidth(400);
        scrollPane.setMaxHeight(700);
        scrollPane.setMaxWidth(800);
        if (generatorGUI.getHeight() > 150 && generatorGUI.getWidth() > 150) {
            primaryStage.setMaximized(true);
            primaryStage.setFullScreen(true);
        }

        GridPane.setConstraints(scrollPane, 1, 0);
        mainPane.getChildren().add(scrollPane);

        GridPane.setConstraints(boardPane, 2, 0);
        mainPane.getChildren().add(boardPane);


        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                if (timer !=null) {
                    timer.cancel();
                }
            }
        });

        primaryStage.setScene(new Scene(mainPane));
        primaryStage.show();

    }


    private static void replaceGeneration(Generator generator, GeneratorGUI generatorGUI, Generation generation) {

        Board playBoard;
        generatorGUI.fillPlayBoard();
        generatorGUI.updateBoard();
        generation.pushBoard(generatorGUI.getPlayBoard());
        playBoard = generatorGUI.getPlayBoard();
        playBoard = generator.runGeneration(playBoard, 0);
        generatorGUI.setPlayBoard(playBoard);
        generatorGUI.updateBoard();

    }

    private Board readBoardFromFile(int width, int height, Label alertLabel) {

        Board board;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose board file or cancel for deafault size 100 x 100");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                board = FileIO.readBoardFromFile(selectedFile, width, height, alertLabel);
            } catch (IOException e1) {
                board = new Board(100, 100);
                e1.printStackTrace();
            }

        } else {
            board = new Board(100, 100);
        }
        return board;

    }



}
