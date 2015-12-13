/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umusic.gui.choicebox;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SingleSelectionModel;
import umusic.uMusicAppData;
import umusic.uMusicPercussionTrack;

/**
 *
 * @author bruce.sailer
 */
public class TrackTypeController implements Initializable {

    @FXML
    ChoiceBox cbTypes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        uMusicPercussionTrack percussionTrack = uMusicAppData.getInstance().getSongController().getPercussionTrack();
        if (percussionTrack != null) {
            cbTypes.getItems().remove("Drum");
        } 
    }

}
