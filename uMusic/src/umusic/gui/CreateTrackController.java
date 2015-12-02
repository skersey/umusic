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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import umusic.uMusicAppData;
import umusic.uMusicTrack.TrackNumber;

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
        uMusicAppData.getInstance().addTrack(ctName.getText(), ctType.getSelectionModel().getSelectedItem().toString(), ctInstrument.getSelectionModel().getSelectedItem().toString());
        Stage stage = (Stage) ctContainer.getScene().getWindow();
        stage.close();
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
    }

}
