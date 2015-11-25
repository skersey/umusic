/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umusic.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author bruce.sailer
 */
public class TrackRecordController implements Initializable {

    @FXML
    BorderPane trContainer;

    @FXML
    TextField trName;

    @FXML
    Label trType;

    @FXML
    ChoiceBox trInstrument;

    @FXML
    Slider trVolume;

    @FXML
    Button trEditButton;

    @FXML
    Button trRemoveButton;

    @FXML
    private void editTrack(ActionEvent event) throws IOException {
        String type = trType.getText();
        Node editor = null;
        switch (type.toLowerCase()) {
            case "melody":
                editor = FXMLLoader.load(getClass().getResource("MelodyTrackEditor.fxml"));
                break;
            case "rhythm":
                break;
            case "drum":
                break;
            default:
                System.out.println("Unknown track type: " + type);
        }
        if (editor != null) {
            Stage stage = (Stage) trContainer.getScene().getWindow();
            Scene scene = stage.getScene();
            BorderPane mainLayout = (BorderPane) scene.lookup("#mainLayout");
            mainLayout.setCenter(editor);
        }
    }

    @FXML
    private void removeTrack(ActionEvent event) throws IOException {

        Stage stage = (Stage) trContainer.getScene().getWindow();
        Scene scene = stage.getScene();
        VBox tracksContainer = (VBox) scene.lookup("#tracks");
        tracksContainer.getChildren().remove(trContainer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
