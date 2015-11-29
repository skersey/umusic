package umusic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import javafx.stage.FileChooser;

/**
 *
 * @author Joshua
 */
public class uMusicFileController {
    
    private uMusicSongController sc;
    String json;
    
    public uMusicFileController(uMusicSongController sc){
        this.sc = sc;
    }
    
    public void save(String fileName){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(sc);
        
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("resource/test" + fileName + "fix.json"), "utf-8"))) {
                 writer.write(json);
        } catch (IOException e){
            System.out.println("fail");
        }
        System.out.println("File Saved.");
    }
    
    public uMusicSongController load(String fileName){    
        try(
            Reader reader = new InputStreamReader(uMusicSongController.class.getResourceAsStream("resource/test" + fileName + "fix.json"), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            sc = gson.fromJson(reader, uMusicSongController.class);
        }
        catch(IOException e){
            System.out.println("Incorrect JSON format");
        }
        return sc;
    }
    
    public void save_gui(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Song...");
        fileChooser.getExtensionFilters().add(
         new FileChooser.ExtensionFilter("Song Files", "*.json"));
        File file = fileChooser.showSaveDialog(umusic.UMusic.stage);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        json = gson.toJson(sc);
        
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(json);
            fileWriter.close();
            
        } catch (IOException e){
            System.out.println("fail");
        }
        System.out.println("File Saved.");
    }
    
    public uMusicSongController open_gui(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Song...");
        fileChooser.getExtensionFilters().add(
         new FileChooser.ExtensionFilter("Song Files", "*.json"));
        File file = fileChooser.showOpenDialog(umusic.UMusic.stage);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        json = "";
        if (file.toString() != null){
        try {
            Files.lines(file.toPath()).forEachOrdered(s-> json+=s);
            // fileReader = new FileReader(file);
            System.out.println(json);
            
            
        } catch (IOException e){
            System.out.println("fail");
            return null;
        }
        System.out.println("File Loaded.");
        
        }
        return gson.fromJson(json, uMusicSongController.class);
    }
}
