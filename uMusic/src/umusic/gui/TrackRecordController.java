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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import umusic.uMusicAppData;
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
    CheckBox trMute;

    @FXML
    Button trEditButton;

    @FXML
    Button trStopButton;

    @FXML
    Button trPauseButton;

    @FXML
    Button trPlayButton;

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

    public void setTrackListener() {
        trName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov,
                    String old_val, String new_val) {
                uMusicAppData.getInstance().getSongController().setTrackName(getTrackNumber(), trName.getText());
            }
        });
        trVolume.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                uMusicAppData.getInstance().getSongController().setTrackVolume(getTrackNumber(), (int) trVolume.getValue());
            }
        });
        trInstrument.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                trInstrument.getSelectionModel().select(trInstrument.getSelectionModel().getSelectedIndex());
                uMusicAppData.getInstance().getSongController().setInstrument(getTrackNumber(), trInstrument.getSelectionModel().getSelectedItem().toString());
            }
        });
    }

    public String getInstrument() {
        return trInstrument.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    private void muteTrack(ActionEvent event) throws IOException {
        final int MUTE = 0;

        if (trType.getText().equalsIgnoreCase("drum")) {
            if (trMute.isSelected()) {
                uMusicAppData.getInstance().getSongController().setPercussionTrackVolume(MUTE);
            } else {
                uMusicAppData.getInstance().getSongController().setPercussionTrackVolume((int) trVolume.getValue());
            }
        } else if (trMute.isSelected()) {
            uMusicAppData.getInstance().getSongController().setTrackVolume(getTrackNumber(), MUTE);
        } else {
            uMusicAppData.getInstance().getSongController().setTrackVolume(getTrackNumber(), (int) trVolume.getValue());
        }
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
                loader = new FXMLLoader(getClass().getResource("DrumTrackEditor.fxml"));
                editor = loader.load();
                controller = (DrumTrackEditorController) loader.getController();
                controller.setTrackRecord(this);
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
    void playTrack() {
        String type = trType.getText();
        if ("drum".equalsIgnoreCase(type)) {
            uMusicAppData.getInstance().getPlayerController().startTrack(TrackNumber.TRACKMAX);
        } else {
            uMusicAppData.getInstance().getPlayerController().startTrack(getTrackNumber());
        }
    }

    @FXML
    void pauseTrack(ActionEvent event) {
        String type = trType.getText();
        if ("drum".equalsIgnoreCase(type)) {
            uMusicAppData.getInstance().getPlayerController().pauseTrack(TrackNumber.TRACKMAX);
        } else {
            uMusicAppData.getInstance().getPlayerController().pauseTrack(getTrackNumber());
        }
    }

    @FXML
    void stopTrack(ActionEvent event) {
        String type = trType.getText();
        if ("drum".equalsIgnoreCase(type)) {
            uMusicAppData.getInstance().getPlayerController().finishTrack(TrackNumber.TRACKMAX);
        } else {
            uMusicAppData.getInstance().getPlayerController().finishTrack(getTrackNumber());
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

    public void setVolume(double volume) {
        trVolume.setValue(volume);
    }

    public double getVolume() {
        return trVolume.getValue();
    }

    public void disableInstrument() {

        trInstrument.setDisable(true);
    }
}
