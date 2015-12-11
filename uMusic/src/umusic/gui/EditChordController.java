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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import umusic.uMusicAppData;
import umusic.uMusicNote;
import umusic.uMusicNote.Inversion;
import umusic.uMusicNote.SharpFlat;
import umusic.uMusicNote.uMusicChord;
import umusic.uMusicTrack.TrackNumber;

/**
 * FXML Controller class
 *
 * @author bruce.sailer and bkersey
 */
public class EditChordController implements Initializable {

    @FXML
    AnchorPane editChordWindow;

    @FXML
    ChoiceBox mteNote;

    @FXML
    ChoiceBox mteChord;

    @FXML
    ChoiceBox mteOctave;

    @FXML
    ToggleGroup durationGroup;

    @FXML
    ToggleGroup sharpFlatGroup;

    @FXML
    ToggleGroup inversionGroup;

    @FXML
    CheckBox mteRest;
    
    @FXML
    CheckBox mteDotted;

    int noteIndex;
    TrackNumber trackNumber;
    
    private uMusicNote getNote() {
        String note = "R";
        int duration = 0;
        int octave = 1;
        SharpFlat sf = SharpFlat.NONE;
	Inversion inv = Inversion.NONE;
	uMusicChord chord = uMusicChord.MAJOR; 
        boolean dotted = false;

        if (!mteRest.isSelected()) {
            note = mteNote.getSelectionModel().getSelectedItem().toString();
        }

	chord = chord.getChordFromString(mteChord.getSelectionModel().getSelectedItem().toString());
	
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

        RadioButton selectedSharpFlat = (RadioButton) sharpFlatGroup.getSelectedToggle();
        String sharpFlatStr = selectedSharpFlat.getText();
        switch (sharpFlatStr) {
            case "sharp":
                sf = SharpFlat.SHARP;
                break;
            case "flat":
                sf = SharpFlat.FLAT;
                break;
            default:
                sf = SharpFlat.NONE;
                break;
        }
        octave = Integer.valueOf(mteOctave.getSelectionModel().getSelectedItem().toString());

        RadioButton selectedInversion = (RadioButton) inversionGroup.getSelectedToggle();
        String invStr = selectedInversion.getText();
	switch (invStr) {
            case "1":
                inv = Inversion.SINGLE;
                break;
            case "2":
                inv = Inversion.DOUBLE;
                break;
            default:
                inv = Inversion.NONE;
                break;
        }

        dotted = mteDotted.isSelected();
	
	return new uMusicNote (note, duration, octave, sf, chord, inv, dotted);
    }

    @FXML
    private void editNoteAction(ActionEvent event) throws IOException {
        StringBuilder sb = new StringBuilder();
        uMusicNote note = getNote();
        uMusicAppData.getInstance().getSongController().editNote(trackNumber, noteIndex, note);

        Stage stage = (Stage) editChordWindow.getScene().getWindow();
        stage.close();
    }

    public void setNoteData (int noteIndex, TrackNumber trackNumber) {
	this.noteIndex = noteIndex;
	this.trackNumber = trackNumber; 
    }

    @FXML
    public void closeButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) editChordWindow.getScene().getWindow();

        stage.close();
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
        	mteNote.getSelectionModel().select(3);
        	mteOctave.getSelectionModel().select(4);
		uMusicChord c = uMusicChord.NONE;
		mteChord.getItems().setAll(java.util.Arrays.asList(c.values()));
        	mteChord.getSelectionModel().select(7);
	}
}
