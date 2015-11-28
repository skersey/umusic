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
    void createButtonAction(ActionEvent even) throws IOException {
        TrackNumber trackNumber = uMusicAppData.getInstance().getSongController().addTrack(ctName.getText());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("TrackRecord.fxml"));
        BorderPane trackRecord = (BorderPane) loader.load();
        TrackRecordController trController = loader.getController();

        trController.setTrackNumber(trackNumber);
        trController.setTrackName(ctName.getText());
        trController.setType((String) ctType.getSelectionModel().getSelectedItem());
        trController.setInstrument(ctInstrument.getSelectionModel().getSelectedIndex());
	// set the instrument in the songController
        uMusicAppData.getInstance().getSongController().setInstrument(trackNumber, ctInstrument.getSelectionModel().getSelectedItem().toString());
        // add the track record to the song editor
        uMusicAppData.getInstance().getSongEditor().getChildren().add(trackRecord);

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
