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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author bruce.sailer
 */
public class SongControlsController implements Initializable {

    @FXML
    ToolBar songController;

    @FXML
    Button addTrackButton;
    
    @FXML
    private void addTrack(ActionEvent event) throws IOException {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("CreateTrack.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Create New Track");
            stage.setScene(new Scene(root, 450, 450));
            Window owner = songController.getScene().getWindow();
            stage.initOwner(owner);
//            stage.initOwner();
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UMusicFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addTrackButton.setDisable(true);
    }

}
