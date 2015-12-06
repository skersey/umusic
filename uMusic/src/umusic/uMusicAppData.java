/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umusic;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import umusic.gui.MainControlsController;
import umusic.gui.SongControlsController;
import umusic.gui.TrackRecordController;
import umusic.uMusicTrack.TrackNumber;

/**
 *
 * @author bruce.sailer
 */
public class uMusicAppData {

    private static final String ID_SONG_EDITOR = "songEditorNode";
    private static uMusicAppData instance = null;

    private uMusicSongController songController = null;
    private final uMusicPlayerController playerController = new uMusicPlayerController();
    private Scene rootScene;
    private VBox songEditorNode;
    private MainControlsController mainControlsController;
    private SongControlsController songControlsController;
    private Stage stage;
    private File lastFile;

    protected uMusicAppData() {
    }

    public static uMusicAppData getInstance() {
        // make this thread safe in case there's another part of the application
        // that is trying to access this application data.
        if (instance == null) {
            synchronized (uMusicAppData.class) {
                if (instance == null) {
                    instance = new uMusicAppData();
                }
            }
        }
        return instance;
    }

    public uMusicAppData closeSong() {
        songController = null;
        return instance;
    }

    public uMusicAppData initSongEditor() throws IOException {
        songEditorNode = new VBox();
        songEditorNode.setId(ID_SONG_EDITOR);

        List<uMusicTrack> tracks = Arrays.asList(songController.getTracks());
        for (uMusicTrack track : tracks) {
            if (track != null) {
                this.addTrackToSongEditor(track);
            }
        }
        return instance;
    }

    public void addTrackToSongEditor(uMusicTrack track) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/TrackRecord.fxml"));
        BorderPane trackRecord = (BorderPane) loader.load();
        TrackRecordController trController = loader.getController();

        trController.setTrackNumber(track.getTrackNumber());
        trController.setTrackName(track.getTrackName());
        trController.setType(track.getType());
        trController.setInstrument(track.getInstrument());
        trController.setTrackListener();

        // add the track record to the song editor
        songEditorNode.getChildren().add(trackRecord);
    }

    public void addTrack(String type, String trackName, String instrument) throws IOException {
        if (songController != null) {
            uMusicTrack.TrackNumber trackNumber = uMusicTrack.TrackNumber.TRACKMAX;
            if ("drum".equalsIgnoreCase(type)) {
                songController.addPercussionTrack(trackName);
            }
            trackNumber = songController.addTrack(type, trackName, instrument);
            addTrackToSongEditor(songController.getTrack(trackNumber));
        }
    }

    public VBox getSongEditor() {
        return songEditorNode;
    }

    public uMusicAppData showSongEditor() {
        BorderPane mainLayout = getMainLayout();
        mainLayout.setCenter(songEditorNode);
        return instance;
    }

    public BorderPane getMainLayout() {
        return (BorderPane) rootScene.lookup("#mainLayout");
    }

    public uMusicSongController getSongController() {
        return songController;
    }

    public void playSong() {
        playerController.startSong();
    }

    public void pauseSong() {
        playerController.pauseSong();
    }

    public void stopSong() {
        playerController.finishSong();
    }

    public uMusicPlayerController getPlayerController() {
        return playerController;
    }

    public uMusicAppData setRootScene(Scene scene) {
        rootScene = scene;
        return instance;
    }

    public MainControlsController getMainControlsController() {
        return mainControlsController;
    }

    public void setMainControlsController(MainControlsController controller) {
        this.mainControlsController = controller;
    }

    public void setSongControlsController(SongControlsController controller) {
        this.songControlsController = controller;
    }

    public SongControlsController getSongControlsController() {
        return songControlsController;
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setSongController(uMusicSongController sc) throws IOException {
        this.songController = sc;
        playerController.addSong(sc);

        SongControlsController scController = uMusicAppData.getInstance().getSongControlsController();
        scController.setTitle(songController.getName());
        scController.setTimeSignature(songController.getTimeSignatureString());
        scController.setTempo(songController.getTempo());
        scController.enableControls();

        initSongEditor();
        showSongEditor();
    }

    public void createSong(String name, String tempo, String timeSignature) throws IOException {
        int split = timeSignature.indexOf("/");
        String top = timeSignature.substring(0, split);
        String bottom = timeSignature.substring(split + 1);

        uMusicSongController sc = new uMusicSongController(name, tempo, Integer.valueOf(top), Integer.valueOf(bottom));
        setSongController(sc);
    }

    public void setLastFile(File file) {
        this.lastFile = file;
    }

    public File getLastFile() {
        return this.lastFile;
    }

    public File getLastFileDir() {
        if (this.lastFile != null) {
            return this.lastFile.getParentFile();
        } else {
            return null;
        }
    }
}
