/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umusic.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bruce.sailer
 */
public class CreateTrackController implements Initializable {

    @FXML
    VBox createTrack;

    @FXML
    void createButtonAction(ActionEvent even) throws IOException {
        Stage stage = (Stage) createTrack.getScene().getWindow();
        BorderPane trackRecord = (BorderPane)FXMLLoader.load(getClass().getResource("TrackRecord.fxml"));

        Scene ownerScene = stage.getOwner().getScene();
        BorderPane mainLayout = (BorderPane)ownerScene.lookup("#mainLayout");
        VBox center = (VBox)mainLayout.getCenter();
        center.getChildren().add(trackRecord);
        stage.close();
    }

    @FXML
    public void closeButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) createTrack.getScene().getWindow();

        stage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
