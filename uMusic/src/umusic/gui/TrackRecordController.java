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
import umusic.uMusicAppData;
import umusic.uMusicNote.uMusicChord;
import umusic.uMusicTrack.TrackNumber;

/**
 *
 * @author bruce.sailer
 */
public class TrackRecordController implements Initializable {

    private TrackNumber trackNumber;

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

    public void setTrackName(String name) {
        trName.setText(name);
    }

    public void setType(String type) {
        trType.setText(type);
    }

    public void setInstrument(int index) {
        trInstrument.getSelectionModel().select(index);
    }

    public void setInstrument(String index) {
        trInstrument.getSelectionModel().select(index);
    }

    public String getInstrument() {
        return trInstrument.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    private void editTrack(ActionEvent event) throws IOException {
        String type = trType.getText();
        Node editor = null;
        FXMLLoader loader = null;
        TrackEditorController controller = null;
        switch (type.toLowerCase()) {
            case "melody":
                loader = new FXMLLoader(getClass().getResource("MelodyTrackEditor.fxml"));
                editor = loader.load();
                controller = (MelodyTrackEditorController) loader.getController();
                controller.setTrackRecord(this);
                break;
            case "chord":
                loader = new FXMLLoader(getClass().getResource("ChordTrackEditor.fxml"));
                editor = loader.load();
                controller = (ChordTrackEditorController) loader.getController();
                controller.setTrackRecord(this);
                break;
            case "drum":
                editor = FXMLLoader.load(getClass().getResource("DrumTrackEditor.fxml"));
                break;
            default:
                System.out.println("Unknown track type: " + type);
        }
        if (editor != null) {
            if (controller != null) {
                controller.setTrackNumber(trackNumber);
                controller.refreshEditor();
            }
            Stage stage = (Stage) trContainer.getScene().getWindow();
            Scene scene = stage.getScene();
            BorderPane mainLayout = (BorderPane) scene.lookup("#mainLayout");
            mainLayout.setCenter(editor);
        }
    }

    @FXML
    private void removeTrack(ActionEvent event) throws IOException {
        uMusicAppData.getInstance().getSongController().deleteTrack(trackNumber);
        uMusicAppData.getInstance().getSongEditor().getChildren().remove(trContainer);
    }

    public void setTrackNumber(TrackNumber trackNumber) {
        this.trackNumber = trackNumber;
    }

    public TrackNumber getTrackNumber() {
        return this.trackNumber;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
