/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umusic.gui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import umusic.uMusicAppData;
import umusic.uMusicFileController;
import umusic.uMusicSongController;

/**
 *
 * @author bruce.sailer
 */
public class MainControlsController implements Initializable {

    @FXML
    private Label label;

    @FXML
    VBox mainController;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @FXML
    private void createSong(ActionEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("CreateSong.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Create New Song");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainControlsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void saveSong() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Song...");
        if (uMusicAppData.getInstance().getLastFile() != null) {
            fileChooser.setInitialDirectory(uMusicAppData.getInstance().getLastFileDir());
            fileChooser.setInitialFileName(uMusicAppData.getInstance().getLastFileName());
        }
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Song Files", "*.json"));
        File file = fileChooser.showSaveDialog(umusic.UMusic.stage);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(uMusicAppData.getInstance().getSongController());

        if (file != null) {
            uMusicAppData.getInstance().setLastFile(file);
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(json);
                fileWriter.close();

            } catch (IOException e) {
                System.out.println("fail");
            }
            System.out.println("File Saved.");
        }
    }

    @FXML
    private void openSong() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(uMusicAppData.getInstance().getLastFileDir());
        fileChooser.setTitle("Open Song...");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Song Files", "*.json"));
        fileChooser.setInitialDirectory(uMusicAppData.getInstance().getLastFileDir());
        File file = fileChooser.showOpenDialog(umusic.UMusic.stage);
        uMusicAppData.getInstance().setLastFile(file);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = new String(Files.readAllBytes(file.toPath()));

        uMusicSongController sc = gson.fromJson(json, uMusicSongController.class);
        uMusicAppData.getInstance().setSongController(sc);

    }

    @FXML
    private void addTrack(ActionEvent event) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("CreateTrack.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Create New Track");
        stage.setScene(new Scene(root, 450, 450));
//        Window owner = songControlsContainer.getScene().getWindow();
//        stage.initOwner(owner);
        stage.show();
    }

    @FXML
    private void handleExit(ActionEvent event) throws IOException {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        uMusicAppData.getInstance().setMainControlsController(this);
    }
}
