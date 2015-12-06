/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umusic.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
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
public class ChordTrackEditorController extends TrackEditorController implements Initializable {

    @FXML
    ScrollPane sheetMusicScroll;

    @FXML
    HBox sheetMusicPane;

    @FXML
    HBox sheetMusicKeyboard;
    
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

	return new uMusicNote (note, duration, octave, sf, chord, inv);
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
        refreshEditor();
    }

    @Override
    public ChordTrackEditorController refreshEditor() {
        sheetMusicPane.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 0;");
        sheetMusicPane.getChildren().clear();
        sheetMusicPane.getChildren().addAll(renderTrackDisplay());
        sheetMusicScroll.setHvalue(1.0); 
        return this;
    }

    private uMusicNote getNoteFromKeyboard(String pitch, int octave) {
        String note = "R";
        int duration = 0;
        SharpFlat sf = SharpFlat.NONE;
	uMusicChord chord = uMusicChord.MAJOR; 
	Inversion inv = Inversion.NONE;

        if (!mteRest.isSelected()) {
            note = pitch;
        }
        RadioButton selectedDuration = (RadioButton) durationGroup.getSelectedToggle();
	chord = chord.getChordFromString(mteChord.getSelectionModel().getSelectedItem().toString());
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
	
	return new uMusicNote (note, duration, octave, sf, chord, inv);
    }

    
    public void addNoteCallback(String pitch, int octave) {
	uMusicNote note = getNoteFromKeyboard(pitch, octave);
        uMusicAppData.getInstance().getPlayerController().setLiveInstrument(getTrackRecord().getInstrument().toUpperCase());
        uMusicAppData.getInstance().getPlayerController().playLiveNote(note, 100);
        uMusicAppData.getInstance().getSongController().addNoteToTrack(getTrackNumber(), note);
        refreshEditor();
    }
    
    private List<Node> renderTrackDisplay() {
        List<Node> trackRender = new ArrayList<Node>();
        MelodyTrackEditorGraphic graphic = new MelodyTrackEditorGraphic();
        trackRender.add(new ImageView(new Image("umusic/gui/img/staff/standard.png")));
        StackPane ts = new StackPane();
        Label tsLabel = new Label();
	tsLabel.setText(" ");
        tsLabel.setContentDisplay(ContentDisplay.BOTTOM);
        ts = graphic.displayTimeSignature();
        tsLabel.setGraphic(ts);
        trackRender.add(tsLabel);

        ArrayList<uMusicNote> trackNotes = uMusicAppData.getInstance().getSongController().getTrackNotes(getTrackNumber());
        int noteIndex = 0;
        for (uMusicNote note : trackNotes) {
            Label noteLabel = new Label();
            noteLabel.setText(note.getChordLessString());
            NoteLabelData data = new NoteLabelData(getTrackNumber(), getTrackRecord(), noteIndex++);
            noteLabel.setUserData(data);
            graphic = new MelodyTrackEditorGraphic();
            noteLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    if (event.getButton().equals(MouseButton.SECONDARY)) {
                        final ContextMenu contextMenu = new ContextMenu();
                        MenuItem removeNote = new MenuItem("Remove");

                        MenuItem editNote = new MenuItem("Edit");
                        removeNote.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                NoteLabelData data = (NoteLabelData) noteLabel.getUserData();
                                uMusicAppData.getInstance().getSongController().deleteNote(getTrackNumber(), data.noteIndex);
                                refreshEditor();
                            }
                        });
                        editNote.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }
                        });
                        contextMenu.getItems().addAll(removeNote, editNote);
                        noteLabel.setContextMenu(contextMenu);
                    }
                }
            });
            GridPane gp = new GridPane();
            gp = graphic.parseNote(noteLabel.getText());
            
            if (gp.getChildren().size() > 1){
		noteLabel.setFont(Font.font(8));
                noteLabel.setText(note.getChordString());
            }
            noteLabel.setContentDisplay(ContentDisplay.BOTTOM);
            noteLabel.setGraphic(gp);
            trackRender.add(noteLabel);
        }
        return trackRender;
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

	uMusicChord c = uMusicChord.NONE;
	mteChord.getItems().setAll(java.util.Arrays.asList(c.values()));
        mteChord.getSelectionModel().select(7);
	sheetMusicScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	sheetMusicScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        Keyboard key;
        sheetMusicKeyboard.getChildren().add(key = new Keyboard(this));
    }

    private class NoteLabelData {

        TrackNumber trackNumber;
        TrackRecordController trackRecord;
        int noteIndex;

        public NoteLabelData(TrackNumber trackNumber, TrackRecordController trackRecord, int noteIndex) {
            this.trackNumber = trackNumber;
            this.trackRecord = trackRecord;
            this.noteIndex = noteIndex;
        }

        public TrackNumber getTrackNumber() {
            return trackNumber;
        }

        public TrackRecordController getTrackRecord() {
            return trackRecord;
        }

        public int getNoteIndex() {
            return noteIndex;
        }
    }
}
