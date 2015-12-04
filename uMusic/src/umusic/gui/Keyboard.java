/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umusic.gui;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Devin
 */
public class Keyboard extends ScrollPane {
    
    private static final Image BLACKKEY = new Image("umusic/gui/img/keyboard/black-key.png");
    private static final Image WHITEKEY = new Image("umusic/gui/img/keyboard/white-key.png");
    private static final Double WHITEWIDTH = WHITEKEY.getWidth();
    private static final Double WHITEHEIGHT = WHITEKEY.getHeight();
    private static final Double BLACKHEIGHT = BLACKKEY.getHeight();
    
    //Change number of octives keyboard covers here
    private static final int NUM_OCTAVES = 7;
    
    private Key[] keys; //array of all keys - haven't implemented - maybe unnecessary
    private int key_counter; //keeps track of current key
    private int xPos; //X axis position of next key
    private int octave_counter; //keeps track of current octave
    
    public Keyboard (){
        //create black and white keys. +1 white key at end of keyboard so the
        //kbd does not terminate with a black key. there are 7 white keys and 5
        //black keys per octave.
        WhiteKey[] whiteKeys = new WhiteKey[NUM_OCTAVES * 7 + 1];
        BlackKey[] blackKeys = new BlackKey[NUM_OCTAVES * 5];
        
        //load pitches to white keys
        String[] pitches1 = {"A","B","C","D","E","F","G"};
        Group background = new Group();
        key_counter = 0;
        
        //Set pitches around midrange. Assuming 1 thru 9 octaves so 5 is about
        //the center.
        octave_counter = 4 - NUM_OCTAVES / 2;
        
        xPos = 0;       
        for (Key key1 : whiteKeys){
            key1 = new WhiteKey();
            key1.setPitch(pitches1[key_counter]);
            key1.setOctave(octave_counter);
            key_counter++;

            //add white keys to Keyboard
            background.getChildren().add(key1);
            key1.setTranslateX(xPos);
            xPos+=WHITEWIDTH;
            if (key_counter >= pitches1.length) {
                key_counter = 0;
                octave_counter++;
            }
        }
        
        //load pitches to black keys
        String[] pitches2 = {"A#","C#","D#","F#","G#"};
        Group foreground = new Group();
        key_counter = 0;
        
        //Set pitches around midrange
        octave_counter = 4 - NUM_OCTAVES / 2;
        
        xPos = 0;
        for (Key key2 : blackKeys){
            System.out.println(xPos);
            key2 = new BlackKey();
            key2.setPitch(pitches2[key_counter]);
            key2.setOctave(octave_counter);
            key_counter++;           
            
            //add black keys to Keyboard
            foreground.getChildren().add(key2);
            key2.setTranslateX(xPos);
            if ((key_counter == 1) || (key_counter == 3))
                    xPos += WHITEWIDTH;
            xPos += WHITEWIDTH;
            if (key_counter >= pitches2.length) {
                key_counter = 0;
                octave_counter++;
            }
        }
        
        //Position the black keys to the top of the white keys
        foreground.setTranslateY((BLACKHEIGHT - WHITEHEIGHT)/2);
        StackPane keyboardPane = new StackPane();
        keyboardPane.getChildren().addAll(background, foreground);
        
        //Add the keyboard to the root scrollpane
        this.setContent(keyboardPane);
        this.setVbarPolicy(ScrollBarPolicy.NEVER);
        this.setHbarPolicy(ScrollBarPolicy.ALWAYS);
        this.setMinHeight(WHITEHEIGHT);
    }

    private class Key extends ImageView {
        int octave;
        String pitch;
        
        private Key(){
            //sends pitch/octave out
            this.setOnMouseClicked(e -> System.out.println(pitch + octave));
        }

        private void setPitch(String pitch) {
            this.pitch = pitch;
        }

        private void setOctave(int octave) {
            this.octave = octave;
        }
    }
    
    private class BlackKey extends Key {
        
        private BlackKey(){
            this.setImage(BLACKKEY);
        }
    }
    
    private class WhiteKey extends Key {
        
        private WhiteKey(){
            this.setImage(WHITEKEY);
        }
    }        
}