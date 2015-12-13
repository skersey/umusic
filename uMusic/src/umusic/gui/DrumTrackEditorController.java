/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umusic.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import umusic.RhythmBank;
import umusic.uMusicAppData;
import umusic.uMusicRhythm;
import umusic.uMusicRhythm.Instrument;
import umusic.uMusicTrack;
import umusic.uMusicTrack.TrackNumber;

/**
 * FXML Controller class
 *
 * @author bruce.sailer
 */
public class DrumTrackEditorController extends TrackEditorController {

    final static int BEAT_BREAKDOWN_FACTOR = 4;
    @FXML
    HBox dteSequenceList;

    @FXML
    ScrollPane dteRhythmEditor;

    @FXML
    VBox beatBankButtons;

    @FXML
    private void addRhythm(ActionEvent event) throws IOException {
        TextInputDialog dialog = new TextInputDialog("Beat Name");
        dialog.setTitle("Enter Beat Name");
        dialog.setHeaderText("Please enter a name for your beat.");
        Optional<String> result = dialog.showAndWait();
        String beatName = null;
        if (result.isPresent()) {
            beatName = result.get();
        }
        int defaultBaseBeat = uMusicAppData.getInstance().getSongController().getTimeSignatureDenominator() * BEAT_BREAKDOWN_FACTOR;
        uMusicRhythm rhythm = new uMusicRhythm(beatName, defaultBaseBeat);

        int totalBeatParts = uMusicAppData.getInstance().getSongController().getTimeSignatureNumerator() * BEAT_BREAKDOWN_FACTOR;
        for (Instrument instrument : Instrument.values()) {
            StringBuilder sb = new StringBuilder();
            for (int n = 0; n < totalBeatParts; n++) {
                sb.append(".");
            }
            System.out.println(instrument.toString() + " " + sb.toString());
            rhythm.setRhythmLayer(instrument, sb.toString());
        }

        Integer rhythmId = uMusicAppData.getInstance().getSongController().getPercussionTrack().getRhythmBank().add(rhythm);
        addBeatControls(rhythmId, rhythm);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void backButtonAction(ActionEvent event) {
        uMusicAppData.getInstance().showSongEditor();
    }

    @FXML
    void playTrack() {
        uMusicAppData.getInstance().getPlayerController().startTrack(TrackNumber.TRACKMAX);
    }

    @FXML
    void pauseTrack(ActionEvent event) {
        uMusicAppData.getInstance().getPlayerController().pauseTrack(TrackNumber.TRACKMAX);
    }

    @FXML
    void stopTrack(ActionEvent event) {
        uMusicAppData.getInstance().getPlayerController().finishTrack(TrackNumber.TRACKMAX);
    }

    @Override
    public TrackEditorController refreshEditor() {
        drawRhthms();
        drawSequence();
        return this;
    }

    private void addBeatControls(Integer rhythmId, uMusicRhythm beatRhythm) {
        VBox beatControls = new VBox();
        Label buttonLabel = new Label(rhythmId + ":" + beatRhythm.getRhythmName());

        final Button editButton = new Button("Edit");
        final Button addButton = new Button("Add");
        final Button playButton = new Button("Play");

        playButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                uMusicAppData.getInstance().getPlayerController().playLiveRhythm(beatRhythm, 125);
            }
        });
        addButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                uMusicAppData.getInstance().getSongController().getPercussionTrack().getTrackSequence().add(rhythmId);
                drawSequence();
            }
        });
        editButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                dteRhythmEditor.setContent(buildRhthmEditor(rhythmId, beatRhythm));
            }
        });

        HBox buttonBox = new HBox();
        HBox playBox = new HBox();
        buttonBox.getChildren().addAll(editButton, addButton, playButton);
        beatControls.getChildren().addAll(buttonLabel, buttonBox, playBox);
        beatBankButtons.getChildren().add(beatControls);

    }

    private String getBeatId(Instrument instrument, int i) {
        return instrument.toString() + "_" + Integer.toString(i);
    }

    private void drawRhthms() {
        RhythmBank rhythmBank = uMusicAppData.getInstance().getSongController().getPercussionTrack().getRhythmBank();
        for (Map.Entry<Integer, uMusicRhythm> rhythmSet : rhythmBank.entrySet()) {
            Integer key = rhythmSet.getKey();
            uMusicRhythm rhythm = rhythmSet.getValue();
            addBeatControls(key, rhythm);
        }
    }

    private void drawSequence() {
        dteSequenceList.getChildren().clear();
        int i = 0;
        for (Integer rhythmId : uMusicAppData.getInstance().getSongController().getPercussionTrack().getTrackSequence()) {
            uMusicRhythm beatRhythm = uMusicAppData.getInstance().getSongController().getPercussionTrack().getRhythmBank().get(rhythmId);
            Button button = new Button(rhythmId + ":" + beatRhythm.getRhythmName());
            Data data = new Data(i);
            button.setUserData(data);
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    if (event.getButton().equals(MouseButton.SECONDARY)) {
                        final ContextMenu contextMenu = new ContextMenu();
                        MenuItem removeNote = new MenuItem("Remove");

                        MenuItem editNote = new MenuItem("Edit");
                        removeNote.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                Data data = (Data) button.getUserData();
                                uMusicAppData.getInstance().getSongController().getPercussionTrack().getTrackSequence().remove(data.getIndex());
                                drawSequence();
                            }
                        });
                        editNote.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                dteRhythmEditor.setContent(buildRhthmEditor(rhythmId, beatRhythm));
                            }
                        });
                        contextMenu.getItems().addAll(removeNote, editNote);
                        button.setContextMenu(contextMenu);
                    }
                }
            });
            dteSequenceList.getChildren().add(button);
            i++;
        }
    }

    private Node buildRhthmEditor(Integer rhythmId, uMusicRhythm rhythm) {
        GridPane rhythmEditor = new GridPane();

        int numBeats = uMusicAppData.getInstance().getSongController().getTimeSignatureNumerator();
        int totalBeats = numBeats * BEAT_BREAKDOWN_FACTOR;
        String rhythmName = rhythm.getRhythmName();

        rhythmEditor.add(new Label(rhythmName), 0, 0);
        int rowNum = 0;

        for (Instrument instrument : Instrument.values()) {
            String rhythmString = rhythm.getRhythmLayer(instrument);
            rowNum++;
            int colNum = 0;
            rhythmEditor.add(new Label(instrument.toString()), colNum, rowNum);
            for (int beatNum = 0; beatNum < numBeats; beatNum++) {
                for (int i = 0; i < BEAT_BREAKDOWN_FACTOR; i++) {
                    int rhythmStrIndex = beatNum * BEAT_BREAKDOWN_FACTOR + i;
                    colNum++;
                    CheckBox cb = new CheckBox();
                    if (rhythmString != null && rhythmString.charAt(rhythmStrIndex) == 'y') {
                        cb.setSelected(true);
                    }
                    cb.setId(getBeatId(instrument, rhythmStrIndex));
                    cb.setOnAction(new EventHandler() {
                        @Override
                        public void handle(Event event) {
                            for (Instrument instrument : Instrument.values()) {
                                StringBuilder sb = new StringBuilder();
                                for (int n = 0; n < totalBeats; n++) {
                                    CheckBox cb = (CheckBox) uMusicAppData.getInstance().getMainLayout().lookup("#" + getBeatId(instrument, n));
                                    sb.append(cb.isSelected() ? "y" : ".");
                                }
                                System.out.println(instrument.toString() + " " + sb.toString());
                                rhythm.setRhythmLayer(instrument, sb.toString());
                            }
                        }
                    });
                    rhythmEditor.add(cb, colNum, rowNum);
                }
                rhythmEditor.add(new Label("|"), ++colNum, rowNum);
            }
        }
        return rhythmEditor;
    }

    private static class Data {

        private final int index;

        private Data(int i) {
            this.index = i;
        }

        public int getIndex() {
            return index;
        }
    }

}
