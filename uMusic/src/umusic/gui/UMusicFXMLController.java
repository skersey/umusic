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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 *
 * @author bruce.sailer
 */
public class UMusicFXMLController implements Initializable {

    @FXML
    private Label label;

    @FXML
    BorderPane root;

    @FXML
    VBox mainController ;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @FXML
    private void removeTrack(ActionEvent event) throws IOException {
        Object source = event.getSource();
        if (source instanceof Button) {
            
            Parent parent = ((Button)source).getParent();
            if (parent instanceof HBox) {
                
                BorderPane rooter = (BorderPane) parent.getParent();
                ((BorderPane)parent.getParent()).getChildren().remove(parent);
            }
        }
    }

    @FXML
    private void addTrack(ActionEvent event) throws IOException {
        Node track = (Node)FXMLLoader.load(getClass().getResource("TrackRecord.fxml"));
    
        if (track instanceof Node) {
            Parent parent = mainController.getParent();
            if (parent instanceof BorderPane) {
                BorderPane bp = (BorderPane) parent;
                Node center = bp.getCenter();
                if (center instanceof VBox) {
         
                    ((VBox) center).getChildren().add((BorderPane)track);
                    
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
