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
import javafx.fxml.FXMLLoader;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import umusic.uMusicAppData;

/**
 * FXML Controller class
 *
 * @author bruce.sailer
 */
public class CreateSongController implements Initializable {

    @FXML
    VBox createSongContainer;

    @FXML
    ChoiceBox tempo;

    @FXML
    TextField title;

    @FXML
    ChoiceBox timeSignature;

    @FXML
    void createButtonAction(ActionEvent even) throws IOException {
        Stage stage = (Stage) createSongContainer.getScene().getWindow();

        Scene ownerScene = stage.getOwner().getScene();

        uMusicAppData.getInstance().createSong(title.getText(), tempo.getSelectionModel().getSelectedItem().toString(), timeSignature.getSelectionModel().getSelectedItem().toString());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SongControls.fxml"));
        ToolBar newSongControls = (ToolBar) loader.load();
        SongControlsController scController = loader.getController();
        Pane existingSongControls = (Pane) ownerScene.lookup("#mcSongControls");
        existingSongControls.getChildren().clear();
        existingSongControls.getChildren().add(newSongControls);

        scController.setTitle(title.getText());
        scController.setTimeSignature(timeSignature.getSelectionModel().getSelectedIndex());
        scController.setTempo(tempo.getSelectionModel().getSelectedIndex());
        scController.enableControls();
        uMusicAppData.getInstance().initSongEditor().showSongEditor();
        stage.close();
    }

    @FXML
    public void closeButtonAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) createSongContainer.getScene().getWindow();

        stage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.timeSignature.getSelectionModel().select(3);
        this.tempo.getSelectionModel().select(10);
    }

}
