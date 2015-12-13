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
import javafx.scene.layout.VBox;
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
    VBox editChordWindow;

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
   
    /**
     * 
     * @return Returns a uMusicNote populated with the data from the edit chord menu
     */
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

    /**
     *	This event fires when a note on the sheet music is right-clicked and 'edit'
     *  is selected.  
     * @param event
     * @throws IOException 
     */
    @FXML
    private void editNoteAction(ActionEvent event) throws IOException {
        StringBuilder sb = new StringBuilder();
        uMusicNote note = getNote();
        uMusicAppData.getInstance().getSongController().editNote(trackNumber, noteIndex, note);

        Stage stage = (Stage) editChordWindow.getScene().getWindow();
        stage.close();
    }

    /**
     * 
     * @param noteIndex The index into the list of notes
     * @param trackNumber The track number that this note should be added to
     */
    public void setNoteData (int noteIndex, TrackNumber trackNumber) {
	this.noteIndex = noteIndex;
	this.trackNumber = trackNumber; 
    }

    /**
     * This event fires when the 'cancel' button is clicked on the edit chord window
     * @param event
     * @throws IOException 
     */
    @FXML
    public void closeButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) editChordWindow.getScene().getWindow();

        stage.close();
    }
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mteNote.getSelectionModel().select(3);
        mteOctave.getSelectionModel().select(4);
        uMusicChord c = uMusicChord.MELODY;
        mteChord.getSelectionModel().select(7);
    }
}
