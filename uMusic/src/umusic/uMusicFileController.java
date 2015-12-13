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
 * 
 * This class saves and loads songs using the JSon format with GSon.
 */
public class uMusicFileController {
    
    /*
    * Takes in a uMusicSongController object and saves its content into a JSon file
    */
    public static void save(String fileName, uMusicSongController sc){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(sc);
        
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("resource/test" + fileName + "fix.json"), "utf-8"))) {
                 writer.write(json);
        } catch (IOException e){
            System.out.println("fail");
            return;
        }
        System.out.println("File Saved.");
    }
    
    /*
    * Takes in a JSon file and loads its content into a uMusicSongController and then returns it
    */
    public static uMusicSongController load(String fileName){    
        uMusicSongController sc = null;
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
}
