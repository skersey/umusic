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
 * 
 * This class provides both a jFugue live player and managed player
 * The live player:
 * 		-accepts jFugue formatted strings as input
 * 		-plays notes/chords as it receives them
 * The managed player:
 * 		-accepts midi formatted sequences as input
 * 		-offers the ability to pause and resume a midi sequence
 */
public class uMusicPlayerController {

    private RealtimePlayer realTime;
    private ManagedPlayer mp;
    private uMusicSongController sc = null;
    private Sequence sequence;
    private boolean trackPlaying;
    private TrackNumber trackNumber;

    public uMusicPlayerController() {
        try {
            realTime = new RealtimePlayer();
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(uMusicPlayerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        mp = new ManagedPlayer();
    }

    /**
     * @param note the uMusic note to play 
     * @param volume the volume to play the note at
     */
    public void playLiveNote(uMusicNote note, int volume) {
        realTime.play(":CON(7," + volume + ") ");
	//for melody and chord, always use voice 0
        realTime.play("V0 "); 
	//convert the uMusicNote to a jfugue formatted string
        realTime.play(note.toString());
    }

    /**
     * 
     * @param instrument the instrument to be played as notes are received
     */
    public void setLiveInstrument(String instrument) {
        realTime.play(" I" + MidiDictionary.INSTRUMENT_STRING_TO_BYTE.get(instrument.toUpperCase()));
    }

    /**
     * @param rhythm the uMusic rhythm to play 
     * @param volume the volume to play the note at
     */
    public void playLiveRhythm(uMusicRhythm rhythm, int volume) {
        realTime.play(":CON(7," + volume + ") ");
	//Percussions must be played on track/voice 9
        realTime.play("V9 ");
        realTime.play(rhythm.toString());
    }

    /**
     * 
     * @param song The uMusicSongController that contains the song to be added
     */
    public void addSong(uMusicSongController song) {
	//Allocate a new managed player when a new song is added
        mp = new ManagedPlayer();
        this.sc = song;
    }

    /**
     * 
     * @param trackNumber Add an individual track to the managed player rather than the entire song
     * @param song The uMusicSongController that contains the track to be added
     */
    public void addTrack(TrackNumber trackNumber, uMusicSongController song) {
        mp = new ManagedPlayer();
        sequence = song.getTrackSequence(trackNumber);
    }

    /**
     * 
     * @param song The uMusicSongController that contains the percussion track to be added
     */
    public void addPercussionTrack(uMusicSongController song) {
        mp = new ManagedPlayer();
        sequence = song.getPercussionTrackSequence();
    }

    /**
     * Starts the song or track that has been added to the managed player
     */
    public void startSong() {
        if (trackPlaying) {
            trackPlaying = false;
            mp.finish();

            System.out.print(mp.isFinished() + "+" + mp.isPaused());
        }
	
	//The startSong overloads start and pause.  If the song is pause, then resume.
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

    /**
     * Pauses the song or track that has been added to the managed player
     */
    public void pauseSong() {
        if (mp.isPlaying()) {
            mp.pause();
        }
    }

    /**
     * Finalizes the song or track that has been added to the managed player
     * When start is issued, the song will start from the beginning.
     */
    public void finishSong() {
        mp.finish();
        trackPlaying = false;
    }

    /**
     * Starts the song or track that has been added to the managed player
     * Validates the trackNumber provided is the trackNumber that was added to the managedPlayer. 
     * @param trackNumber The track number to be started
     */
    public void startTrack(TrackNumber trackNumber) {
        if (mp.isPaused() && trackPlaying && this.trackNumber.toString().equalsIgnoreCase(trackNumber.toString())) {
            mp.resume();
            return;
        }

        trackPlaying = true;
        this.trackNumber = trackNumber;
        if (this.trackNumber == TrackNumber.TRACKMAX) {
            sequence = sc.getPercussionTrackSequence();
        } else {
            sequence = sc.getTrackSequence(trackNumber);
        }
        try {
            mp.start(sequence);
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(uMusicPlayerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(uMusicPlayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Pauses the track that has been added to the managed player
     * Validates the trackNumber provided is the trackNumber that was added to the managedPlayer. 
     * @param trackNumber The track number to be paused 
     */
    public void pauseTrack(TrackNumber trackNumber) {
        if (mp.isPlaying() && this.trackNumber.toString().equalsIgnoreCase(trackNumber.toString())) {
            mp.pause();
        }
    }

    /**
     * Finalizes the track that has been added to the managed player
     * Validates the trackNumber provided is the trackNumber that was added to the managedPlayer. 
     * When start is issued, the track will start from the beginning.
     * @param trackNumber The track number to be finished 
     */
    public void finishTrack(TrackNumber trackNumber) {
        if (this.trackNumber.toString().equalsIgnoreCase(trackNumber.toString())) {
            mp.finish();
            trackPlaying = false;
        }
    }
}
