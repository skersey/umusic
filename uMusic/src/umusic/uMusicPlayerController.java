package umusic;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import org.jfugue.midi.MidiDictionary;
import org.jfugue.player.ManagedPlayer;
import org.jfugue.realtime.RealtimePlayer;
import umusic.uMusicTrack.TrackNumber;

/**
 *
 * @author bkersey
 */
public class uMusicPlayerController {
    private RealtimePlayer realTime;
    private ManagedPlayer mp;
    private uMusicSongController sc = null;
    private Sequence sequence;
    private boolean trackPlaying;
    private TrackNumber trackNumber;
    
    public  uMusicPlayerController() {
	    try {
		realTime = new RealtimePlayer();
	    } catch (MidiUnavailableException ex) {
                Logger.getLogger(uMusicPlayerController.class.getName()).log(Level.SEVERE, null, ex);
	    }

	    mp  = new ManagedPlayer();
    }

    public void playLiveNote(uMusicNote note, int volume) {
	   realTime.play(":CON(7," + volume + ") "); 
	   realTime.play("V0 "); 
	   realTime.play(note.toString());
    }
    
    public void setLiveInstrument(String instrument) {
	    realTime.play(" I" + MidiDictionary.INSTRUMENT_STRING_TO_BYTE.get(instrument.toUpperCase()));
    }

    public void playLiveRhythm(uMusicRhythm rhythm, int volume) {
	   realTime.play(":CON(7," + volume + ") "); 
	   realTime.play("V9 "); 
	   realTime.play(rhythm.toString());
    }

    public void addSong(uMusicSongController song) {
	   mp = new ManagedPlayer();
	   this.sc = song;
    }

    public void addTrack(TrackNumber trackNumber, uMusicSongController song) {
   	   mp = new ManagedPlayer();
           sequence = song.getTrackSequence(trackNumber);
    }
    
    public void addPercussionTrack(uMusicSongController song) {
   	   mp = new ManagedPlayer();
           sequence = song.getPercussionTrackSequence();
    }
    
    public void startSong() {
            if(trackPlaying){
                    trackPlaying = false;
                    mp.finish();
                    
                    System.out.print(mp.isFinished() + "+" +  mp.isPaused());
            }
	    if (mp.isPaused() && !mp.isFinished()) {
		    mp.resume();
		    return;
	    }

           sequence = sc.getSongSequence();
	    try {
		    mp.start(sequence);
	    } catch (InvalidMidiDataException ex) {
		    Logger.getLogger(uMusicPlayerController.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (MidiUnavailableException ex) {
		    Logger.getLogger(uMusicPlayerController.class.getName()).log(Level.SEVERE, null, ex);
	    }
    }

    public void pauseSong() {
	    if (mp.isPlaying())
	    	mp.pause();
    }

    public void finishSong() {
	    mp.finish();
            trackPlaying = false;
    }	
    
        public void startTrack(TrackNumber trackNumber){
        if (mp.isPaused() && trackPlaying && this.trackNumber.toString().equalsIgnoreCase(trackNumber.toString())) {
		    mp.resume();
		    return;
	    }
        
           trackPlaying = true;
           this.trackNumber = trackNumber;
           sequence = sc.getTrackSequence(trackNumber);
	    try {
		    mp.start(sequence);
	    } catch (InvalidMidiDataException ex) {
		    Logger.getLogger(uMusicPlayerController.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (MidiUnavailableException ex) {
		    Logger.getLogger(uMusicPlayerController.class.getName()).log(Level.SEVERE, null, ex);
	    }
    }
        
    public void pauseTrack(TrackNumber trackNumber){
        if (mp.isPlaying() && this.trackNumber.toString().equalsIgnoreCase(trackNumber.toString())){
	    mp.pause();
        }
    }
    
    public void finishTrack(TrackNumber trackNumber){
        if (this.trackNumber.toString().equalsIgnoreCase(trackNumber.toString())){
            mp.finish();
            trackPlaying = false;
        }
    }
}
