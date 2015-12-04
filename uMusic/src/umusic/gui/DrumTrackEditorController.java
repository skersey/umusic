/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umusic.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;

/**
 * FXML Controller class
 *
 * @author bruce.sailer
 */
public class DrumTrackEditorController extends TrackEditorController {

    @FXML
    ScrollPane dteRhythmEditor;

    @FXML
    private void addRhythm(ActionEvent event) throws IOException {
        TextInputDialog dialog = new TextInputDialog("Beat Name");
        dialog.setTitle("Enter Beat Name");
        dialog.setHeaderText("Please enter a name for your beat.");
        Optional<String> result = dialog.showAndWait();
        String beatName = null;
        if (result.isPresent()) {
            
            beatName = result.get();
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BeatEditor.fxml"));
        Node editor = loader.load();
        BeatEditorController controller = (BeatEditorController) loader.getController();
        dteRhythmEditor.setContent(editor);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public TrackEditorController refreshEditor() {
            return this;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
