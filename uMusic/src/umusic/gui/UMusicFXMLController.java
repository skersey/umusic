/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umusic.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 *
 * @author bruce.sailer
 */
public class UMusicFXMLController implements Initializable {

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
            Window owner = mainController.getScene().getWindow();
            stage.initOwner(owner);
//            stage.initOwner();
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UMusicFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
