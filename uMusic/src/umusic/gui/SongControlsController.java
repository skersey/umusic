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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;
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
    Slider masterVolume;

    @FXML
    void playSong() {
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
        Parent root;
        root = FXMLLoader.load(getClass().getResource("CreateTrack.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Create New Track");
        stage.setScene(new Scene(root, 450, 450));
        stage.show();
    }

    public void setTitle(String titleStr) {
        title.setText(titleStr);
    }

    public void setTimeSignature(int index) {
        timeSignature.getSelectionModel().select(index);
    }

    public void setTimeSignature(String timeSignature) {
        this.timeSignature.getSelectionModel().select(timeSignature);
    }
    
    public void setTempo(String tempo) {
        this.tempo.getSelectionModel().select(tempo);
    }
    
    public void setTempo(int index) {
        this.tempo.getSelectionModel().select(index);
    }

    public void setMasterVolumeListener(){
        masterVolume.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                    uMusicAppData.getInstance().getSongController().setMasterVolume((int)masterVolume.getValue());
            }
        });

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
        uMusicAppData.getInstance().setSongControlsController(this);
        disableControls();
    }

}
