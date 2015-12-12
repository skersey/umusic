package umusic.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class ChordTrackEditorController extends TrackEditorController implements Initializable {

    @FXML
    ScrollPane sheetMusicScroll;

    @FXML
    HBox sheetMusicPane;

    @FXML
    HBox sheetMusicKeyboard;
    
    @FXML
    ChoiceBox mteChord;

    @FXML
    ToggleGroup durationGroup;

    @FXML
    ToggleGroup sharpFlatGroup;

    @FXML
    ToggleGroup inversionGroup;

    @FXML
    CheckBox mteDotted;

    @FXML
    Button stopBuuton;
    
    @FXML
    Button pauseBuuton;
    
    @FXML
    Button playBuuton;

    @FXML
    private void addRestAction(ActionEvent event) throws IOException {
	String note = "R";
        int duration = 0;
        boolean dotted = false;
	uMusicChord chord = uMusicChord.MAJOR; 

        RadioButton selectedDuration = (RadioButton) durationGroup.getSelectedToggle();
        String durationStr = selectedDuration.getText();
	chord = chord.getChordFromString(mteChord.getSelectionModel().getSelectedItem().toString());
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

        dotted = mteDotted.isSelected();
        uMusicNote n = new uMusicNote (note, duration, 5, SharpFlat.NONE, chord, Inversion.NONE, dotted);
        uMusicAppData.getInstance().getSongController().addNoteToTrack(getTrackNumber(), n);
        refreshEditor();
    }

    @FXML
    void playTrack() {
        uMusicAppData.getInstance().getPlayerController().startTrack(getTrackNumber());
    }

    @FXML
    void pauseTrack(ActionEvent event) {
        uMusicAppData.getInstance().getPlayerController().pauseTrack(getTrackNumber());
    }

    @FXML
    void stopTrack(ActionEvent event) {
        uMusicAppData.getInstance().getPlayerController().finishTrack(getTrackNumber());
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
        String note = pitch;
        int duration = 0;
        SharpFlat sf = SharpFlat.NONE;
	uMusicChord chord = uMusicChord.MAJOR; 
	Inversion inv = Inversion.NONE;
        boolean dotted = false;

        String noteArray[] = note.split("");
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

        if (note.matches("[A-G]#")){ //if a black keyboard key was pressed           
            //get sharp/flat radio group status and adjust note accordingly
            RadioButton selectedSharpFlat = (RadioButton) sharpFlatGroup.getSelectedToggle();
            String sharpFlatStr = selectedSharpFlat.getText();
            switch (sharpFlatStr) {
                case "sharp":
                    sf = SharpFlat.SHARP;
                    note = note.substring(0,1);
                    break;
                case "flat":
                    sf = SharpFlat.FLAT;
                    //Adjust pitch up if flat e.g. G# = Ab
                    char noteChar = note.charAt(0);
                    if (noteChar == 'G') 
                        note = "A";
                    else note = Character.toString((char)((int)noteChar + 1));
                    break;
                default:
                    sf = SharpFlat.NONE;
                    break;
            }
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
	
        dotted = mteDotted.isSelected();
	return new uMusicNote (note, duration, octave, sf, chord, inv, dotted);
    }

    
    public void addNoteCallback(String pitch, int octave) {
	uMusicNote note = getNoteFromKeyboard(pitch, octave);
        uMusicAppData.getInstance().getPlayerController().setLiveInstrument(getTrackRecord().getInstrument().toUpperCase());
	int volume = uMusicAppData.getInstance().getSongController().getTrackVolume(getTrackNumber());
        uMusicAppData.getInstance().getPlayerController().playLiveNote(note, volume);
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
                                NoteLabelData data = (NoteLabelData) noteLabel.getUserData();
				try {
            				Parent root;

            				FXMLLoader loader;
	    				loader = new FXMLLoader(getClass().getResource("EditChord.fxml"));
            				root = loader.load();
	    				EditChordController edit = loader.getController();
	    				edit.setNoteData(data.noteIndex, data.trackNumber);
            				Stage stage = new Stage();
            				stage.setTitle("Edit Chord");
            				stage.setScene(new Scene(root, 300, 350));
            				stage.showAndWait();
                                	refreshEditor();
        			} catch (IOException ex) {
            				Logger.getLogger(MainControlsController.class.getName()).log(Level.SEVERE, null, ex);
        			}
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
                if (noteLabel.getText().contains("R")){
                    noteLabel.setText(" ");
            } else{
                noteLabel.setFont(Font.font(8));
                noteLabel.setText(note.getChordString()); 
                }
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
