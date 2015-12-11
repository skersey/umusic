/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umusic.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import umusic.uMusicAppData;

/**
 * FXML Controller class
 *
 * @author bruce.sailer
 */
public class CreateTrackController implements Initializable {

    @FXML
    VBox ctContainer;

    @FXML
    TextField ctName;

    @FXML
    ChoiceBox ctType;

    @FXML
    ChoiceBox ctInstrument;

    @FXML
    public void createButtonAction(ActionEvent even) throws IOException {
        String trackName = ctName.getText();
        String trackType = ctType.getSelectionModel().getSelectedItem().toString();
        String instrument = ctInstrument.getSelectionModel().getSelectedItem().toString();
        if (trackName == null || trackName.trim().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING, "You must provide a track name", ButtonType.OK);
            alert.showAndWait();
        } else {
            uMusicAppData.getInstance().addTrack(trackType, trackName, instrument);
            Stage stage = (Stage) ctContainer.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    public void cancelButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ctContainer.getScene().getWindow();

        stage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.ctInstrument.getSelectionModel().select(2);
        this.ctType.getSelectionModel().select(0);
        ctType.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue ov,
                    Number value, Number new_value) {
                if (new_value.equals(2)) {
                    ctInstrument.setDisable(true);
                } else {
                    ctInstrument.setDisable(false);
                }
            }
        });
    }

}
