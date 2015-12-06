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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
public class CreateSongController implements Initializable {

    @FXML
    VBox createSongContainer;

    @FXML
    ChoiceBox tempo;

    @FXML
    TextField title;

    @FXML
    ChoiceBox timeSignature;

    @FXML
    void createButtonAction(ActionEvent even) throws IOException {
        String titleStr = title.getText();
        String tempoStr = tempo.getSelectionModel().getSelectedItem().toString();
        String timeSignatureStr = timeSignature.getSelectionModel().getSelectedItem().toString();
        if (titleStr == null || titleStr.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "You must provide a song title", ButtonType.OK);
            alert.showAndWait();
        } else {
            uMusicAppData.getInstance().createSong(titleStr, tempoStr, timeSignatureStr);
            SongControlsController scController = uMusicAppData.getInstance().getSongControlsController();
            scController.setTitle(titleStr);
            scController.setTimeSignature(timeSignature.getSelectionModel().getSelectedIndex());
            scController.setTempo(tempo.getSelectionModel().getSelectedIndex());
            scController.setMasterVolumeListener();
            scController.enableControls();
            uMusicAppData.getInstance().initSongEditor().showSongEditor();

            Stage stage = (Stage) createSongContainer.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    public void closeButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) createSongContainer.getScene().getWindow();

        stage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.timeSignature.getSelectionModel().select(3);
        this.tempo.getSelectionModel().select(10);
    }

}
