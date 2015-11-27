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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import umusic.uMusicAppData;
import umusic.uMusicNote;
import umusic.uMusicNote.SharpFlat;
import umusic.uMusicTrack.TrackNumber;

/**
 * FXML Controller class
 *
 * @author bruce.sailer
 */
public class MelodyTrackEditorController extends TrackEditorController implements Initializable {

    @FXML
    TextArea mteMusic;

    @FXML
    ChoiceBox mteNote;

    @FXML
    ChoiceBox mteOctave;

    @FXML
    ToggleGroup durationGroup;

    @FXML
    CheckBox mteRest;

    @FXML
    CheckBox mteDotted;

    private uMusicNote getNote() {
        String note = "R";
        int duration = 0;
        int octave = 1;
        SharpFlat sf = SharpFlat.NONE;
        boolean dotted = false;

        if (!mteRest.isSelected()) {
            note = (String) mteNote.getSelectionModel().getSelectedItem();
        }
        RadioButton selectedDuration = (RadioButton) durationGroup.getSelectedToggle();
        String durationStr = selectedDuration.getText();
        switch (durationStr) {
            case ("whole"):
                duration = 1;
                break;
            case ("half"):
                duration = 2;
                break;
            case ("quarter"):
                duration = 4;
                break;
            case ("eighth"):
                duration = 8;
                break;
            case ("sixteenth"):
                duration = 16;
                break;
        }
        octave = Integer.valueOf((String) mteOctave.getSelectionModel().getSelectedItem());
        dotted = mteDotted.isSelected();
        return new uMusicNote(note, duration, octave, sf, dotted);
    }

    @FXML
    private void playNote(ActionEvent event) throws IOException {
        uMusicAppData.getInstance().getPlayerController().setLiveInstrument(getTrackRecord().getInstrument().toUpperCase());
        uMusicAppData.getInstance().getPlayerController().playLiveNote(getNote(), 100);
    }

    @FXML
    private void addNote(ActionEvent event) throws IOException {
        StringBuilder sb = new StringBuilder();
        TrackNumber tn = getTrackNumber();
        uMusicNote note = getNote();
        uMusicAppData.getInstance().getPlayerController().setLiveInstrument(getTrackRecord().getInstrument().toUpperCase());
        uMusicAppData.getInstance().getPlayerController().playLiveNote(note, 100);
        uMusicAppData.getInstance().getSongController().addNoteToTrack(getTrackNumber(), note);

        this.mteMusic.setText(uMusicAppData.getInstance().getSongController().getTrackNotes(getTrackNumber()).toString());
    }

    @FXML
    private void backButtonAction(ActionEvent event) {
        uMusicAppData.getInstance().showSongEditor();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mteNote.getSelectionModel().select(3);
        mteOctave.getSelectionModel().select(4);
    }

}
