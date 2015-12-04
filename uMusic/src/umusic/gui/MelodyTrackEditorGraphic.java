/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umusic.gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import umusic.uMusicAppData;

/**
 *
 * @author Joshua
 */
public class MelodyTrackEditorGraphic {
    
        Label noteLabel;
        String note;
        StackPane stack = new StackPane();
        GridPane grid = new GridPane();
        Image image;
        Image image2;
        ImageView iv;
        
    public MelodyTrackEditorGraphic(){

    }
    
    public StackPane displayTimeSignature(){
        String file = "umusic/gui/img/timeSignature/";
        int TimeSigNum = uMusicAppData.getInstance().getSongController().getTimeSignatureNumerator();
        int TimeSigDen = uMusicAppData.getInstance().getSongController().getTimeSignatureDenominator();
        
        switch (TimeSigNum){
            case 2: 
                switch (TimeSigDen) {
                    case (2): 
                        file = file + "1"; 
                        break;
                    case (4):
                        file = file + "2"; 
                        break;
                }
                break;
            case 3: 
                file = file + "3"; 
                break;
            case 4:
                file = file + "4"; 
                break;
            case 6:
                file = file + "5"; 
                break;
        }

        System.out.println(file);
        createGraphicStack(file);
        return stack;
    }
    public GridPane parseNote(String note){
        String[] noteArray = note.split("");
        String file = "umusic/gui/img/";
        if(noteArray.length == 3){
            switch(noteArray[2]){
                
                case "w": file = file + "rest/w"; createStaff(5); break;
                case "h": file = file + "rest/h"; createStaff(4); break;
                case "q": file = file + "rest/q"; createStaff(3); break;
                case "i": file = file + "rest/i"; createStaff(2); break;
                case "s": file = file + "rest/s"; createStaff(1);break;
            }
            createGraphicGridNote(file);
        }
        if(noteArray.length == 4){
            if (noteArray[2].equalsIgnoreCase("1") | noteArray[2].equalsIgnoreCase("2") |
                    noteArray[2].equalsIgnoreCase("3") | noteArray[2].equalsIgnoreCase("4") |
                    noteArray[2].equalsIgnoreCase("5") | noteArray[2].equalsIgnoreCase("6") |
                    noteArray[2].equalsIgnoreCase("7")){
                switch (noteArray[3]){
                    case "w": file = file + "wholeNote/"; createStaff(5); break;
                    case "h": file = file + "halfNote/";  createStaff(4);break;
                    case "q": file = file + "quarterNote/"; createStaff(3);break;
                    case "i": file = file + "eighthNote/"; createStaff(2);break;
                    case "s": file = file + "sixteenthNote/"; createStaff(1);break;
                }

                file = file + noteArray[1] + noteArray [2];
                createGraphicGridNote(file); 
            }
            if (noteArray[1].equals("R")){
                if (noteArray[3].equals(".")){
                    switch (noteArray[2]){
                        case "w": file = file + "rest/wDot"; System.out.println("hi"); createStaff(5); break;
                        case "h": file = file + "rest/hDot"; createStaff(4); break;
                        case "q": file = file + "rest/qDot"; createStaff(3); break;
                        case "i": file = file + "rest/iDot"; createStaff(2); break;
                        case "s": file = file + "rest/sDot"; createStaff(1); break;
                    }
                } else { 
                    switch (noteArray[3]){
                        case "w": file = file + "rest/w"; createStaff(5); break;
                        case "h": file = file + "rest/h"; createStaff(4); break;
                        case "q": file = file + "rest/q"; createStaff(3); break;
                        case "i": file = file + "rest/i"; createStaff(2); break;
                        case "s": file = file + "rest/s"; createStaff(1); break;
                }
                }
                createGraphicGridNote(file);
            }   
        }
        if(noteArray.length == 5){
          if (noteArray[3].equalsIgnoreCase("1") | noteArray[3].equalsIgnoreCase("2") |
                    noteArray[3].equalsIgnoreCase("3") | noteArray[3].equalsIgnoreCase("4") |
                    noteArray[3].equalsIgnoreCase("5") | noteArray[3].equalsIgnoreCase("6") |
                    noteArray[3].equalsIgnoreCase("7")){
                switch(noteArray[2]){
                    case "b": 
                        createGraphicGridNote("umusic/gui/img/flat/" + noteArray[1] + noteArray[3]);
                        break;
                    case "#":
                        createGraphicGridNote("umusic/gui/img/sharp/" + noteArray[1] + noteArray[3]);
                        break;
                }
                switch (noteArray[4]){
                    case "w": file = file + "wholeNote/"; createStaff(5); break;
                    case "h": file = file + "halfNote/";  createStaff(4);break;
                    case "q": file = file + "quarterNote/"; createStaff(3);break;
                    case "i": file = file + "eighthNote/"; createStaff(2);break;
                    case "s": file = file + "sixteenthNote/"; createStaff(1);break;
                }

                file = file + noteArray[1] + noteArray [3];
                createGraphicGridNote(file);
            }
            
         if (noteArray[2].equalsIgnoreCase("1") | noteArray[2].equalsIgnoreCase("2") |
                    noteArray[2].equalsIgnoreCase("3") | noteArray[2].equalsIgnoreCase("4") |
                    noteArray[2].equalsIgnoreCase("5") | noteArray[2].equalsIgnoreCase("6") |
                    noteArray[2].equalsIgnoreCase("7")){
                if(!noteArray[4].equalsIgnoreCase(".")){
                    file = file + noteArray[1] + noteArray [3];
                    createGraphicGridNote(file);
                } else {
                    createGraphicGridNote("umusic/gui/img/dot/" + noteArray[1] + noteArray[2]);
                    switch (noteArray[3]){
                        case "w": file = file + "wholeNote/"; createStaff(5); break;
                        case "h": file = file + "halfNote/";  createStaff(4);break;
                        case "q": file = file + "quarterNote/"; createStaff(3);break;
                        case "i": file = file + "eighthNote/"; createStaff(2);break;
                        case "s": file = file + "sixteenthNote/"; createStaff(1);break;
                    }

                file = file + noteArray[1] + noteArray [2];
                createGraphicGridNote(file); 
                }
            }
            if (noteArray[1].equals("R")){
                switch (noteArray[3]){
                    case "w": file = file + "rest/wDot"; System.out.println("hi"); createStaff(5); break;
                    case "h": file = file + "rest/hDot"; createStaff(4); break;
                    case "q": file = file + "rest/qDot"; createStaff(3); break;
                    case "i": file = file + "rest/iDot"; createStaff(2); break;
                    case "s": file = file + "rest/sDot"; createStaff(1); break;
                }
                createGraphicGridNote(file);
            }
        }
        if(noteArray.length == 6){
          if (noteArray[3].equalsIgnoreCase("1") | noteArray[3].equalsIgnoreCase("2") |
                    noteArray[3].equalsIgnoreCase("3") | noteArray[3].equalsIgnoreCase("4") |
                    noteArray[3].equalsIgnoreCase("5") | noteArray[3].equalsIgnoreCase("6") |
                    noteArray[3].equalsIgnoreCase("7")){
                switch(noteArray[2]){
                    case "b": 
                        createGraphicGridNote("umusic/gui/img/flat/" + noteArray[1] + noteArray[3]);
                        break;
                    case "#":
                        createGraphicGridNote("umusic/gui/img/sharp/" + noteArray[1] + noteArray[3]);
                        break;
                }
                switch (noteArray[4]){
                    case "w": file = file + "wholeNote/"; createStaff(5); break;
                    case "h": file = file + "halfNote/";  createStaff(4);break;
                    case "q": file = file + "quarterNote/"; createStaff(3);break;
                    case "i": file = file + "eighthNote/"; createStaff(2);break;
                    case "s": file = file + "sixteenthNote/"; createStaff(1);break;
                }

                file = file + noteArray[1] + noteArray [3];
                createGraphicGridNote("umusic/gui/img/dot/" + noteArray[1] + noteArray[3]);
                createGraphicGridNote(file);
            } 
        }   

        return grid;
    }
    public void createGraphicGridNote(String file){
        System.out.println(file);
        image = new Image(file + ".png");
        iv = new ImageView();
        iv.setImage(image);
        grid.add(iv, 1, 0);
    }
    
    public void createGraphicGridStaff(String file){
        System.out.println(file);
        image = new Image(file + ".png");
        iv = new ImageView();
        iv.setImage(image);
        grid.add(iv, 2, 0);
    }
    
    public void createGraphicStack(String file){
        System.out.println(file);
        image = new Image(file + ".png");
        iv = new ImageView();
        iv.setImage(image);
        stack.getChildren().add(iv);
    }
    
    public void createStaff(int staff){
        
        switch(staff){
            case(1): 
                createGraphicGridStaff("umusic/gui/img/staff/s");
                break;
            case(2): 
                createGraphicGridStaff("umusic/gui/img/staff/i");
                break;
            case(3): 
                createGraphicGridStaff("umusic/gui/img/staff/q");
                break;
            case(4): 
                createGraphicGridStaff("umusic/gui/img/staff/h");
                break;
            case(5): 
                createGraphicGridStaff("umusic/gui/img/staff/w");
                break;
        }
    }
}
