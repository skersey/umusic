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
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author bruce.sailer
 */
public class DrumTrackEditorController implements Initializable {

    @FXML
    ChoiceBox mteNote;
    
    @FXML
    ChoiceBox mteOctave;

    @FXML
    ToggleGroup durationGroup;

    @FXML
    CheckBox mteRest;

    @FXML
    CheckBox mteDotted;

    @FXML
    private void addNote(ActionEvent event) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (mteRest.isSelected()) {
            sb.append("Rest");
        } else {
            sb.append(mteNote.getSelectionModel().getSelectedItem());
        }
        RadioButton selectedToggle = (RadioButton) durationGroup.getSelectedToggle();
        sb.append(": ").append(selectedToggle.getText());
        if (mteDotted.isSelected()) {
            sb.append(": dotted");
        }

        System.out.println("Adding Note! " + sb.toString());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
