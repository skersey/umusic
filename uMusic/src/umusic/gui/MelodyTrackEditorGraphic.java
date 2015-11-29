/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umusic.gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Joshua
 */
public class MelodyTrackEditorGraphic {
    
        Label noteLabel;
        String note;
        String[] noteArray;
        StackPane stack = new StackPane();
        Image image;
        ImageView iv;
        
    public MelodyTrackEditorGraphic(Label noteLabel){
        this.noteLabel = noteLabel;
        this.note = noteLabel.getText();
    }
    
    public StackPane parseNote(){
        
        String[] noteArray = note.split("");
        String file = "umusic/gui/img/melody/";
        if(noteArray.length == 4){
            if (noteArray[2].equalsIgnoreCase("4") | noteArray[2].equalsIgnoreCase("5")){
                switch (noteArray[3]){
                    case "w": file = file + "wholeNote/"; createStaff(1); break;
                    case "h": file = file + "halfNote/";  createStaff(1);break;
                    case "q": file = file + "quarterNote/"; createStaff(1);break;
                    case "i": file = file + "eighthNote/"; createStaff(1);break;
                    case "s": file = file + "sixteenthNote/"; createStaff(1);break;
                }
                switch (noteArray[2]){
                    case "4":
                        if(noteArray[1].equalsIgnoreCase("C")){
                            createGraphic("umusic/gui/img/melody/line/C4");
                        } 
                        break;
                }
                file = file + noteArray[1] + noteArray [2];
                createGraphic(file); 
            } //else createStaff(1);
        }
        if(noteArray.length == 5){
            if (noteArray[3].equalsIgnoreCase("4") | noteArray[3].equalsIgnoreCase("5")){
                switch(noteArray[2]){
                    case "b": 
                        createGraphic("umusic/gui/img/melody/flat/" + noteArray[1] + noteArray[3]);
                        break;
                    case "#":
                        createGraphic("umusic/gui/img/melody/sharp/" + noteArray[1] + noteArray[3]);
                        break;
                }
                switch (noteArray[4]){
                    case "w": file = file + "wholeNote/"; createStaff(1); break;
                    case "h": file = file + "halfNote/";  createStaff(1);break;
                    case "q": file = file + "quarterNote/"; createStaff(1);break;
                    case "i": file = file + "eighthNote/"; createStaff(1);break;
                    case "s": file = file + "sixteenthNote/"; createStaff(1);break;
                }
                switch (noteArray[3]){
                    case "4":
                        if(noteArray[1].equalsIgnoreCase("C")){
                            createGraphic("umusic/gui/img/melody/line/C4");
                        } 
                        break;
                }
                file = file + noteArray[1] + noteArray [3];
                createGraphic(file);
            } //else createStaff(1);
            
            if (noteArray[2].equalsIgnoreCase("4") | noteArray[2].equalsIgnoreCase("5")){
                if(!noteArray[4].equalsIgnoreCase(".")){
                    file = file + noteArray[1] + noteArray [3];
                    createGraphic(file);
                } else {
                    createGraphic("umusic/gui/img/melody/dot/" + noteArray[1] + noteArray[2]);
                    switch (noteArray[3]){
                        case "w": file = file + "wholeNote/"; createStaff(1); break;
                        case "h": file = file + "halfNote/";  createStaff(1);break;
                        case "q": file = file + "quarterNote/"; createStaff(1);break;
                        case "i": file = file + "eighthNote/"; createStaff(1);break;
                        case "s": file = file + "sixteenthNote/"; createStaff(1);break;
                    }
                    switch (noteArray[2]){
                    case "4":
                        if(noteArray[1].equalsIgnoreCase("C")){
                            createGraphic("umusic/gui/img/melody/line/C4");
                        } 
                        break;
                    }   
                file = file + noteArray[1] + noteArray [2];
                createGraphic(file); 
                }
            }
        }
        if(noteArray.length == 6){
            if (noteArray[3].equalsIgnoreCase("4") | noteArray[3].equalsIgnoreCase("5")){
                switch(noteArray[2]){
                    case "b": 
                        createGraphic("umusic/gui/img/melody/flat/" + noteArray[1] + noteArray[3]);
                        break;
                    case "#":
                        createGraphic("umusic/gui/img/melody/sharp/" + noteArray[1] + noteArray[3]);
                        break;
                }
                switch (noteArray[4]){
                    case "w": file = file + "wholeNote/"; createStaff(1); break;
                    case "h": file = file + "halfNote/";  createStaff(1);break;
                    case "q": file = file + "quarterNote/"; createStaff(1);break;
                    case "i": file = file + "eighthNote/"; createStaff(1);break;
                    case "s": file = file + "sixteenthNote/"; createStaff(1);break;
                }
                switch (noteArray[3]){
                    case "4":
                        if(noteArray[1].equalsIgnoreCase("C")){
                            createGraphic("umusic/gui/img/melody/line/C4");
                        } 
                        break;
                }
                file = file + noteArray[1] + noteArray [3];
                createGraphic("umusic/gui/img/melody/dot/" + noteArray[1] + noteArray[3]);
                createGraphic(file);
            } 
        }   
        return stack;
    }
    public void createGraphic(String file){
        
        image = new Image(file + ".png");
        iv = new ImageView();
        iv.setImage(image);
        stack.getChildren().add(iv);
    }
    
    public void createStaff(int staff){
        
        switch(staff){
            case(1): 
                createGraphic("umusic/gui/img/melody/staff/s");
                break;
            /*case(2): 
                createGraphic("umusic/gui/img/melody/staff/i");
                break;
            case(3): 
                createGraphic("umusic/gui/img/melody/staff/q");
                break;
            case(4): 
                createGraphic("umusic/gui/img/melody/staff/h");
                break;
            case(6): 
                createGraphic("umusic/gui/img/melody/staff/w");
                break;*/
        }
    }
}
