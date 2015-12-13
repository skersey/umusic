package umusic;

import java.util.ArrayList;
import java.util.Arrays;
import javax.sound.midi.Sequence;
import org.jfugue.midi.MidiDictionary;
import org.jfugue.player.Player;
import umusic.uMusicTrack.TrackNumber;

/**
 *
 * @author bkersey
 */
public class uMusicSongController {

    private int masterVolume = 100;
    private int trackVolume = 100;
    private int percussionVolume = 100;
    private int timeSignatureNumerator = 4;
    private int timeSignatureDenominator = 4;
    private String tempo = "Allegro";
    private String name;
    private uMusicTrack[] trackList;
    private uMusicPercussionTrack percussionTrack;
    public ArrayList<String> tempoList = new ArrayList<>(Arrays.asList("Grave",
            "Largo", "Larghetto", "Lento", "Adagio", "Adagietto", "Andante",
            "Andantino", "Moderato", "Allegretto", "Allegro", "Vivace",
            "Presto", "Pretissimo"));

    public uMusicSongController(String name, String tempo, int timeSignatureNumerator, int timeSignatureDenominator) {
        this();
        this.name = name;
        this.tempo = tempo;
        this.timeSignatureNumerator = timeSignatureNumerator;
        this.timeSignatureDenominator = timeSignatureDenominator;
    }

    public uMusicSongController() {
        trackList = new uMusicTrack[TrackNumber.TRACKMAX.ordinal()];
        for (int i = 0; i < TrackNumber.TRACKMAX.ordinal(); i++) {
            trackList[i] = null;
        }
    }

    /**
     * 
     * @param name Sets the name for this song controller
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @param volume Sets the master volume for this song controller
     */
    public int setMasterVolume(int volume) {
        if (volume > 125 || volume < 0) {
            return -1;
        }

        this.masterVolume = volume;
        setAllTrackVolume();
        return 0;
    }

    /**
     * Sets all of the track volumes.  This is called after the master
     * volume has been modified.
     */
    public void setAllTrackVolume(){

        for(int i = 0; i < trackList.length; i++){
            if(trackList[i] != null){
                setTrackVolume(trackList[i].getTrackNumber(), trackVolume);
            }
        }
        if (percussionTrack != null){
            setPercussionTrackVolume(percussionVolume);
        }
    }

    /**
     * 
     * @param numerator sets the time signature numerator
     * @param denominator sets the time signature denominator
     */
    public void setTimeSignature(int numerator, int denominator) {
        this.timeSignatureNumerator = numerator;
        this.timeSignatureDenominator = denominator;
    }

    /**
     * 
     * @param signature Sets the time signature for song controller
     */
    public void setTimeSignature(String signature){
        String[] signatureArray = signature.split("/");
        this.timeSignatureNumerator = Integer.parseInt(signatureArray[0]);
        this.timeSignatureDenominator = Integer.parseInt(signatureArray[1]);
        
    }

    /**
     * 
     * @param tempo Sets the tempo for this song controller
     * @return 
     */
    public int setTempo(String tempo) {
        if (tempoList.contains(tempo) == false) {
            System.out.println("The tempo name is not valid: " + tempo);
            return -1;
        }

        this.tempo = tempo;
        return 0;
    }

    /**
     * Add a new track to the song controller
     * @param trackType Defines the track type (melody or chord)
     * @param trackName Sets the name of the track
     * @param instrument Sets the track instrument
     * @return Returns the TrackNumber of the new track 
     */
    public TrackNumber addTrack(String trackType, String trackName, String instrument) {
        for (int i = 0; i < TrackNumber.TRACKMAX.ordinal(); i++) {
            if (trackList[i] == null) {
                TrackNumber tn = TrackNumber.values()[i];
                trackList[i] = new uMusicTrack(tn, trackType, trackName, instrument);

                return tn;
            }
        }

        return TrackNumber.TRACKMAX;
    }

    /**
     * 
     * @param trackName Sets the name of the track
     * @return Returns the TrackNumber of the new track 
     */
    public TrackNumber addTrack(String trackName) {
        for (int i = 0; i < TrackNumber.TRACKMAX.ordinal(); i++) {
            if (trackList[i] == null) {
                TrackNumber tn = TrackNumber.values()[i];
                trackList[i] = new uMusicTrack(tn, "Melody", trackName);

                return tn;
            }
        }

        return TrackNumber.TRACKMAX;
    }

    /**
     * 
     * @param trackNumber delete the track with the provided trackNumber
     */
    public void deleteTrack(TrackNumber trackNumber) {
        trackList[trackNumber.ordinal()] = null;
    }

    /**
     * Deletes all of the current tracks for the track controller
     */
    public void deleteAllTracks() {
        for (int i = 0; i < TrackNumber.TRACKMAX.ordinal(); i++) {
            trackList[i] = null;
        }

        percussionTrack = null;
    }

    /**
     * 
     * @param trackNumber the target track
     * @param note Add this note to the track 
     */
    public void addNoteToTrack(TrackNumber trackNumber, uMusicNote note) {
        trackList[trackNumber.ordinal()].addNextNote(note);
    }

    /**
     * 
     * @param trackNumber The track number that contains the note 
     * @param arrayIndex The index into the array of uMusicNotes
     * @param note replace the current note at arrayIndex with the note provided 
     */
    public void editNote(TrackNumber trackNumber, int arrayIndex, uMusicNote note) {
        trackList[trackNumber.ordinal()].editNote(arrayIndex, note);
    }

    /**
     * 
     * @param trackNumber The track number that contains the note 
     * @param arrayIndex The index into the array of uMusicNotes
     */
    public void deleteNote(TrackNumber trackNumber, int arrayIndex) {
        trackList[trackNumber.ordinal()].deleteNote(arrayIndex);
    }

    /**
     * 
     * @param trackNumber The track number that contains the note 
     */
    public void deleteLastNote(TrackNumber trackNumber) {
        trackList[trackNumber.ordinal()].deleteLastNote();
    }

    /**
     * 
     * @param trackNumber The track number of the track 
     * @param trackName The name of the track
     */
    public void setTrackName(TrackNumber trackNumber, String trackName){
        trackList[trackNumber.ordinal()].setName(trackName);
    }

    /**
     * 
     * @param trackNumber The track number of the track 
     * @param volume Sets the track volume to this value; adjusted by the master volume
     * @return 
     */
    public int setTrackVolume(TrackNumber trackNumber, int volume) {
        int v;
        trackVolume = volume;
        if (volume == 0 || masterVolume == 0) {
            v = 0;
        } else {
            v = (volume * masterVolume) / 125;
        }
        return trackList[trackNumber.ordinal()].setVolume(v);
    }

    /**
     * 
     * @param trackNumber
     * @return Returns the track volume of the provided trackNumber
     */
    public int getTrackVolume(TrackNumber trackNumber) {
        return trackList[trackNumber.ordinal()].getVolume();
    }

    /**
     * 
     * @param trackNumber the track number of the desired track 
     * @param instrument Sets the track instrument
     */
    public int setInstrument(TrackNumber trackNumber, String instrument) {
        return trackList[trackNumber.ordinal()].setInstrument(instrument);
    }

    /**
     * 
     * @param trackNumber the track number that contains the notes
     * @return Returns an array of uMusicNotes 
     */
    public ArrayList<uMusicNote> getTrackNotes(TrackNumber trackNumber) {
        return trackList[trackNumber.ordinal()].getTrackNotes();
    }

    /**
     * 
     * @return Returns an Array of UmusicTracks 
     */
    public uMusicTrack[] getTracks() {
        return this.trackList;
    }

    /**
     * 
     * @return Returns the current masterVolume for this song controller 
     */
    public int getMasterVolume() {
        return masterVolume;
    }

    /**
     * 
     * @return Returns the uMusicPercussionTrack for this song controller 
     */
    public uMusicPercussionTrack getPercussionTrack() {
        return percussionTrack;
    }
   
    /**
     * 
     * @param trackName sets the name of the percussion track
     */
    public void addPercussionTrack(String trackName) {
        percussionTrack = new uMusicPercussionTrack(trackName);
    }

    /**
     * deletes the percussion track
     */
    public void deletePercussionTrack() {
        percussionTrack = null;
    }

    /**
     * 
     * @param volume Sets the volume for the percussion track
     */
    public int setPercussionTrackVolume(int volume) {
        int v;
        percussionVolume = volume;
        if (volume == 0 || masterVolume == 0) {
            v = 0;
        } else {
            v = (volume * masterVolume) / 125;
        }
        percussionTrack.setVolume(v);
        return v;
    }

    /**
     * 
     * @return Returns a list of all of the support tempos
     */
    public ArrayList<String> getSupportedTempos() {
        return (ArrayList<String>) tempoList.clone();
    }

    /**
     * 
     * @return a Sequence that contains all of the information necessary to play
     * the song in the jFugue managed player
     */
    public Sequence getSongSequence() {
        String song = "";
        song += " TIME:" + timeSignatureNumerator + "/" + timeSignatureDenominator;
        song += " T" + MidiDictionary.TEMPO_STRING_TO_INT.get(tempo.toUpperCase());

        for (int i = 0; i < TrackNumber.TRACKMAX.ordinal(); i++) {
            if (trackList[i] != null) {
                song += trackList[i].buildTrackString();
            }
        }

        if (percussionTrack != null) {
            song += percussionTrack.toStaccatoString();
        }

        System.out.println(song);
        return new Player().getSequence(song);
    }

    /**
     * 
     * @param trackNumber
     * @return a Sequence for the trackNumber provided that contains all of the 
     * information necessary to play the track in the jFugue managed player
     */
    public Sequence getTrackSequence(TrackNumber trackNumber) {
        String song = "";
        song += " TIME:" + timeSignatureNumerator + "/" + timeSignatureDenominator;
        song += " T" + MidiDictionary.TEMPO_STRING_TO_INT.get(tempo.toUpperCase());

        if (trackList[trackNumber.ordinal()] != null) {
            song += trackList[trackNumber.ordinal()].buildTrackString();
        }

        System.out.println(song);
        return new Player().getSequence(song);
    }

    /**
     * 
     * @return a Sequence that contains all of the information necessary to play
     * the song in the jFugue managed player
     */
    public Sequence getPercussionTrackSequence() {
        String song = "";
        song += " TIME:" + timeSignatureNumerator + "/" + timeSignatureDenominator;
        song += " T" + MidiDictionary.TEMPO_STRING_TO_INT.get(tempo.toUpperCase());

        if (percussionTrack != null) {
            song += percussionTrack.toStaccatoString();
        }

        System.out.println(song);
        return new Player().getSequence(song);
    }

    /**
     * 
     * @return the time signature numerator for the song
     */
    public int getTimeSignatureNumerator() {
        return timeSignatureNumerator;
    }

    /**
     * 
     * @return the time signature denominator for the song
     */
    public int getTimeSignatureDenominator() {
        return timeSignatureDenominator;
    }

    /**
     * 
     * @return return the string representation of the time signature for the song
     */
    public String getTimeSignatureString() {
        return timeSignatureNumerator + "/" + timeSignatureDenominator;
    }

    /**
     * 
     * @return the tempo for the song
     */
    String getTempo() {
        return tempo;
    }

    /**
     * 
     * @return the name of the song
     */
    String getName() {
        return name;
    }

    /**
     * 
     * @param trackNumber 
     * @return the umusicTrack for the provided trackNumber
     */
    uMusicTrack getTrack(TrackNumber trackNumber) {
        return trackList[trackNumber.ordinal()];
    }

}
