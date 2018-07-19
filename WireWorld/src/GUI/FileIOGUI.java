package GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;


public class FileIOGUI {


    private GridPane FileIOPane;
    private Slider slider;
    private Button btChoose;
    private Button btSave;
    private Label lbCurrentFileName;
    private Long timeValue;


    FileIOGUI() {

        FileIOPane = new GridPane();
        FileIOPane.setVgap(10);
        FileIOPane.setVgap(10);
        FileIOPane.setPadding(new Insets(10, 10, 10, 10));
        FileIOPane.setAlignment(Pos.CENTER);
        FileIOPane.setPrefWidth(180);
        FileIOPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btChoose = new Button();
        Label lbNavigation = new Label("Press right button\nmultiple times\nto draw elements");
        Label lbNavigation2 = new Label("Drag with left\nbutton to draw");
        slider = new Slider();
        slider.setMax(1000);
        slider.setMin(10);
        slider.setValue(300);
        slider.setPadding(new Insets(10, 10, 10, 10));



        lbCurrentFileName = new Label("Choose File");
        btChoose.setText("Choose file");


        btChoose.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


        btSave = new Button("Save File");
        btSave.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btSave.setPrefWidth(180);


        GridPane.setConstraints(lbCurrentFileName, 0, 2);
        FileIOPane.getChildren().add(lbCurrentFileName);

        GridPane.setConstraints(lbNavigation, 0, 3);
        FileIOPane.getChildren().add(lbNavigation);

        GridPane.setConstraints(lbNavigation2, 0, 4);
        FileIOPane.getChildren().add(lbNavigation2);

        GridPane.setConstraints(slider, 0, 5);
        FileIOPane.getChildren().add(slider);

        GridPane.setConstraints(btChoose, 0, 0);
        FileIOPane.getChildren().add(btChoose);

        GridPane.setConstraints(btSave, 0, 1);
        FileIOPane.getChildren().add(btSave);

    }

    public GridPane getFileIOPane() {
        return FileIOPane;
    }

    public void setFileIOPane(GridPane fileIOPane) {
        FileIOPane = fileIOPane;
    }

    public Label getLbCurrentFileName() {
        return lbCurrentFileName;
    }

    public void setLbCurrentFileName(String lbCurrentFileName) {
        this.lbCurrentFileName.setText(lbCurrentFileName);
    }


    public Button getBtChoose() {
        return btChoose;
    }


    public Slider getSlider() {
        return slider;
    }

    public void setSlider(Slider slider) {
        this.slider = slider;
    }

    public Button getBtSave() {
        return btSave;
    }

    public void setBtSave(Button btSave) {
        this.btSave = btSave;
    }


}
