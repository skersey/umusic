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
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import umusic.RhythmBank;
import umusic.uMusicAppData;
import umusic.uMusicRhythm;
import umusic.uMusicRhythm.Instrument;

/**
 * FXML Controller class
 *
 * @author bruce.sailer
 */
public class DrumTrackEditorController extends TrackEditorController {

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
        int defaultBaseBeat = uMusicAppData.getInstance().getSongController().getTimeSignatureDenominator() * 4;
        uMusicRhythm rhythm = new uMusicRhythm(beatName, defaultBaseBeat);

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
        final Button playButton = new Button(">");
        final Button pauseButton = new Button("||");
        final Button stopButton = new Button("o");
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
                dteSequenceList.getChildren().add(new Button(rhythmId + ":" + beatRhythm.getRhythmName()));
            }
        });
        editButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                dteRhythmEditor.setContent(buildRhthmEditor(rhythmId, beatRhythm));
            }

            private Node buildControls() {
                HBox controls = new HBox();

                Button playButton = new Button(">");
                Button pauseButton = new Button("||");
                Button stopButton = new Button("o");
                return controls;
            }

            private Node buildRhthmEditor(Integer rhythmId, uMusicRhythm rhythm) {

                VBox editorContainer = new VBox();

                GridPane rhythmEditor = new GridPane();
                int numerator = uMusicAppData.getInstance().getSongController().getTimeSignatureNumerator();
                int denominator = uMusicAppData.getInstance().getSongController().getTimeSignatureDenominator();
                int baseBeatDuration = rhythm.getBaseBeatDuration();
                int numBeats = baseBeatDuration / denominator;
                int totalBeats = numBeats * numerator;
                String rhythmName = rhythm.getRhythmName();
               
                rhythmEditor.add(new Label(rhythmName), 0, 0);
                int rowNum = 0;

                for (Instrument instrument : Instrument.values()) {
                    String rhythmString = rhythm.getRhythmLayer(instrument);
                    rowNum++;
                    int colNum = 0;
                    int beatNum = 0;
                    rhythmEditor.add(new Label(instrument.toString()), colNum, rowNum);
                    for (int n = 0; n < numerator; n++) {
                        for (int i = 0; i < numBeats; i++) {
                            colNum++;
                            CheckBox cb = new CheckBox();
                            if (rhythmString != null && rhythmString.charAt(i) == 'y') {
                                cb.setSelected(true);
                            }
                            cb.setId(getBeatId(instrument, beatNum++));
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

        });

        HBox buttonBox = new HBox();
        HBox playBox = new HBox();
        buttonBox.getChildren().addAll(editButton, addButton);
        playBox.getChildren().addAll(stopButton, pauseButton, playButton);
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
        for (Integer rhythmId : uMusicAppData.getInstance().getSongController().getPercussionTrack().getTrackSequence()) {
            uMusicRhythm beatRhythm = uMusicAppData.getInstance().getSongController().getPercussionTrack().getRhythmBank().get(rhythmId);
            dteSequenceList.getChildren().add(new Button(rhythmId + ":" + beatRhythm.getRhythmName()));
        }
    }

}
