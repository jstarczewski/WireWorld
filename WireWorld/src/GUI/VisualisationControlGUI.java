package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class VisualisationControlGUI {


    private GridPane mainGenerationPane = new GridPane();
    private Button btGenerate;
    private Button btPlay;
    private Button btFirst;
    private Button btNext;
    private Button btPrevious;

    VisualisationControlGUI() {

        mainGenerationPane.setVgap(10);
        mainGenerationPane.setVgap(10);
        mainGenerationPane.setPadding(new Insets(10, 10, 10, 10));
        mainGenerationPane.setPrefWidth(180);


        GridPane generationNavigationControlPane = getGenerationNavigationControlPane();
        generationNavigationControlPane.setAlignment(Pos.CENTER);

        GridPane generationControlPane = getGenerationControlPane();

        GridPane.setConstraints(generationControlPane, 0, 0);
        mainGenerationPane.getChildren().add(generationControlPane);

        GridPane.setConstraints(generationNavigationControlPane, 0, 1);
        mainGenerationPane.getChildren().add(generationNavigationControlPane);
    }


    private GridPane getGenerationControlPane() {

        GridPane generationControlPane = new GridPane();
        generationControlPane.setVgap(10);
        generationControlPane.setHgap(10);


        btGenerate = new Button();
        btGenerate.setText("Generate");
        btGenerate.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btGenerate.setPrefWidth(180);


        GridPane.setConstraints(btGenerate, 0, 0);
        generationControlPane.getChildren().add(btGenerate);


        return generationControlPane;
    }

    private GridPane getGenerationNavigationControlPane() {


        btPlay = new Button(">");
        //  btLast = new Button(">|");
        btFirst = new Button("|<");
        btNext = new Button(">>");
        btPrevious = new Button("<<");
        GridPane generationControlGridPane = new GridPane();
        generationControlGridPane.setPadding(new Insets(5, 5, 5, 5));

        GridPane.setConstraints(btFirst, 0, 0);
        generationControlGridPane.getChildren().add(btFirst);

        GridPane.setConstraints(btPrevious, 1, 0);
        generationControlGridPane.getChildren().add(btPrevious);

        GridPane.setConstraints(btPlay, 2, 0);
        generationControlGridPane.getChildren().add(btPlay);

        GridPane.setConstraints(btNext, 3, 0);
        generationControlGridPane.getChildren().add(btNext);


        return generationControlGridPane;
    }

    public GridPane getMainGenerationPane() {
        return mainGenerationPane;
    }


    public Button getBtGenerate() {
        return btGenerate;
    }

    public Button getBtPlay() {
        return btPlay;
    }

    public Button getBtFirst() {
        return btFirst;
    }

    public Button getBtNext() {
        return btNext;
    }

    public Button getBtPrevious() {
        return btPrevious;
    }


}
