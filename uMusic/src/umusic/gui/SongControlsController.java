/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umusic.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import umusic.uMusicAppData;

/**
 * FXML Controller class
 *
 * @author bruce.sailer
 */
public class SongControlsController implements Initializable {

    @FXML
    ToolBar songControlsContainer;

    @FXML
    TextField title;

    @FXML
    ChoiceBox timeSignature;

    @FXML
    ChoiceBox tempo;

    @FXML
    Button addTrackButton;

    @FXML
    Button stopButton;

    @FXML
    Button pauseButton;

    @FXML
    Button playButton;

    @FXML
    void playSong(ActionEvent event) {
        uMusicAppData.getInstance().playSong();
    }

    @FXML
    void pauseSong(ActionEvent event) {
        uMusicAppData.getInstance().pauseSong();
    }
    
    @FXML
    void stopSong(ActionEvent event) {
        uMusicAppData.getInstance().stopSong();
    }
    @FXML
    private void addTrack(ActionEvent event) throws IOException {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("CreateTrack.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Create New Track");
            stage.setScene(new Scene(root, 450, 450));
            Window owner = songControlsContainer.getScene().getWindow();
            stage.initOwner(owner);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UMusicFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setTitle(String titleStr) {
        title.setText(titleStr);
    }

    public void setTimeSignature(int index) {
        timeSignature.getSelectionModel().select(index);
    }

    public void setTempo(int index) {
        tempo.getSelectionModel().select(index);
    }

    public void disableControls() {
        Node[] controls = {title, timeSignature, tempo, addTrackButton, stopButton, pauseButton, playButton};
        for (Node node : controls) {
            node.setDisable(true);
        }
    }

    public void enableControls() {
        Node[] controls = {title, timeSignature, tempo, addTrackButton, stopButton, pauseButton, playButton};
        for (Node node : controls) {
            node.setDisable(false);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        disableControls();
    }

}
