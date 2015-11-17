package umusic;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import org.jfugue.player.ManagedPlayer;
import org.jfugue.realtime.RealtimePlayer;

public class uMusicPlayerController {
    private RealtimePlayer realTime;
    private ManagedPlayer mp;
    private Sequence sequence;
    
    public  uMusicPlayerController() {
	    try {
		realTime = new RealtimePlayer();
	    } catch (MidiUnavailableException ex) {
                Logger.getLogger(uMusicPlayerController.class.getName()).log(Level.SEVERE, null, ex);
	    }

	    mp  = new ManagedPlayer();
    }

    public void playLiveNote(uMusicNote note, int volume) {
	   realTime.play(":CON(7," + volume + ")"); 
	   realTime.play(note.toString());
    }

    public void addSong(uMusicSongController song) {
        sequence = song.getSongSequence();
    }

    public void startSong() {
	    if (mp.isPaused()) {
		    mp.resume();
	    }

	    try {
		    mp.start(sequence);
	    } catch (InvalidMidiDataException ex) {
		    Logger.getLogger(uMusicPlayerController.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (MidiUnavailableException ex) {
		    Logger.getLogger(uMusicPlayerController.class.getName()).log(Level.SEVERE, null, ex);
	    }
    }

    public void pauseSong() {
	    mp.pause();
    }

    public void finishSong() {
	    mp.finish();
    }	
}
