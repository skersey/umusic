package umusic;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import org.jfugue.player.ManagedPlayer;
import org.jfugue.player.Player;

public class uMusicPlayer implements Runnable {

    private ManagedPlayer mp;

    public void run () {
        System.out.println("Hello from thread");
            mp = new ManagedPlayer();
	}

    public void start () {
        try {
            Player player = new Player();
            String str1 = "V1 I[Piano]   R   R   R   R   R G5q F5q G5q F5q G5q F5q"; 
            String str2 = "V2 I[Flute] D5q G5q F5q C5q D5q D5q G5q F5q C5q D5q";
            Sequence s1 = player.getSequence(str1, str2);

            System.out.println("Start music:begin");
	
            mp.start(s1);
            System.out.println("Start music");
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(uMusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(uMusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pause () {
        System.out.println("Pause music");
        mp.pause();
    }

    public void resume () {
        mp.resume();
    }

    public void finish () {
        mp.finish();
    }
}