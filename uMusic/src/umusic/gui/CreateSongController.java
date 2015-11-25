/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umusic.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bruce.sailer
 */
public class CreateSongController implements Initializable {

    @FXML
    VBox createSong;

    @FXML
    TextField tempo;

    @FXML
    TextField title;

    @FXML
    ChoiceBox timeSignature;

    @FXML
    void createButtonAction(ActionEvent even) throws IOException {
        Stage stage = (Stage) createSong.getScene().getWindow();

        Scene ownerScene = stage.getOwner().getScene();
        TextField targetTitle = (TextField) ownerScene.lookup("#title");
        targetTitle.setText(title.getText());
        
        TextField targetTempo = (TextField) ownerScene.lookup("#tempo");
        targetTempo.setText(tempo.getText());
        
        ChoiceBox targetTimeSignature = (ChoiceBox) ownerScene.lookup("#timeSignature");
        targetTimeSignature.setSelectionModel(timeSignature.getSelectionModel());
        
        Button targetButton = (Button) ownerScene.lookup("#addTrackButton");
        targetButton.setDisable(false);
        
        BorderPane mainLayout = (BorderPane) ownerScene.lookup("#mainLayout");
        VBox tracks = new VBox();
        tracks.setId("tracks");
        mainLayout.setCenter(tracks);
        stage.close();
    }

    @FXML
    public void closeButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) createSong.getScene().getWindow();

        stage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (rb == null) {

        }
    }

}
