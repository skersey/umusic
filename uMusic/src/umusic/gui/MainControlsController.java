/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umusic.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
        uMusicFileController fc = new umusic.uMusicFileController(umusic.UMusic.sc);
        fc.save_gui();       
    }
  
    @FXML
    private void openSong() throws IOException {
        uMusicFileController fc = new umusic.uMusicFileController(umusic.UMusic.sc);
        umusic.UMusic.sc = fc.open_gui();
        
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        uMusicAppData.getInstance().setMainControlsController(this);
    }

}
