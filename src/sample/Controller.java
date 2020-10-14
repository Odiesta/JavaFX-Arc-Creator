package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Ellipse;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Controller {

    @FXML
    private Group mainGroup;

    @FXML
    private Arc arc;

    @FXML
    private TextField centerXField;
    @FXML
    private TextField centerYField;
    @FXML
    private TextField radiusXField;
    @FXML
    private TextField radiusYField;
    @FXML
    private TextField startAngleField;
    @FXML
    private TextField arcLengthField;
    @FXML
    private ComboBox<ArcType> arcType;
    @FXML
    private Button turnLight;

    public void initialize() {
        arcType.setItems(FXCollections.observableArrayList(ArcType.OPEN, ArcType.CHORD, ArcType.ROUND));
        arcType.getSelectionModel().selectFirst();

        arc.setCenterX(400.0f);
        arc.setCenterY(400.0f);
        arc.setRadiusX(300.0f);
        arc.setRadiusY(300.0f);
        arc.setStartAngle(170.0f);
        arc.setLength(15f);
        arc.setType(ArcType.ROUND);

        Path img2 = FileSystems.getDefault().getPath("flashlight.png");
        Image img3 = new Image(img2.toAbsolutePath().toUri().toString());
        ImageView imgView = new ImageView(img3);
        imgView.setFitHeight(200);
        imgView.setFitWidth(200);
        imgView.setX(400.0f);
        imgView.setY(300.0f);
        mainGroup.getChildren().add(imgView);
        turnLight.setOnAction(event -> {
            if (turnLight.getText().equals("On")) {
                arc.setVisible(true);
                turnLight.setText("Off");
            } else {
                arc.setVisible(false);
                turnLight.setText("On");
            }
        });

    }

    @FXML
    public void calculateArc() {
        if (!isTextDigit(centerXField.getText()) || !isTextDigit(centerYField.getText()) ||
                !isTextDigit(radiusXField.getText()) || !isTextDigit(radiusYField.getText()) ||
                !isTextDigit(startAngleField.getText()) || !isTextDigit(arcLengthField.getText())) {
            return;
        }

        arc.setCenterX(Integer.parseInt(centerXField.getText()));
        arc.setCenterY(Integer.parseInt(centerYField.getText()));
        arc.setRadiusX(Integer.parseInt(radiusXField.getText()));
        arc.setRadiusY(Integer.parseInt(radiusYField.getText()));
        arc.setStartAngle(Integer.parseInt(startAngleField.getText()));
        arc.setLength(Integer.parseInt(arcLengthField.getText()));
        arc.setType(arcType.getSelectionModel().getSelectedItem());
    }

    public boolean isTextDigit(String s) {
        return s.matches("\\d+");
    }

}