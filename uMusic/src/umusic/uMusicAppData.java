/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umusic;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

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

    public uMusicAppData initSongEditor() {
        songEditorNode = new VBox();
        songEditorNode.setId(ID_SONG_EDITOR);
        return instance;
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
    
    public uMusicAppData createSong(String name, String tempo, String timeSignature) {
        songController = new uMusicSongController();
        songController.setName(name);
        songController.setTempo(tempo);
        int split = timeSignature.indexOf("/");
        String top = timeSignature.substring(0, split);
        String bottom = timeSignature.substring(split + 1);
        songController.setTimeSignature(Integer.valueOf(top), Integer.valueOf(bottom));
        return instance;
    }

    public uMusicSongController getSongController() {
        return songController;
    }

    public void playSong() {
        playerController.addSong(songController);
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
}
