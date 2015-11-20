package umusic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

/**
 *
 * @author Joshua
 */
public class uMusicFileController {
    
    private uMusicSongController sc;
    
    public uMusicFileController(uMusicSongController sc){
        this.sc = sc;
    }
    
    public void save(String fileName){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(sc);
        
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/resource/" + fileName + ".json"), "utf-8"))) {
                 writer.write(json);
        } catch (IOException e){
            System.out.println("fail");
        }
        System.out.println("File Saved.");
    }
    
    public uMusicSongController load(String fileName){    
        try(
            Reader reader = new InputStreamReader(uMusicSongController.class.getResourceAsStream("/resource/test" + fileName + ".json"), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            sc = gson.fromJson(reader, uMusicSongController.class);
        }
        catch(IOException e){
            System.out.println("Incorrect JSON format");
        }
        return sc;
    }
}
