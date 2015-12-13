package umusic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.jfugue.midi.MidiDictionary;

/**
 *
 * @author bkersey
 * 
 * This class contains all of the information necessary to define a music track.  
 */
public class uMusicTrack {

    private int volume = 100;
    private String instrument = "Piano";
    private String trackName;
    private TrackNumber trackNumber;
    private final ArrayList<uMusicNote> notes = new ArrayList<>();
    public ArrayList<String> InstrumentList = new ArrayList<>(Arrays.asList(
            "Flute", "Piano", "Guitar", "Alto_Sax", "Soprano_Sax", "Clarinet",
            "Violin", "Tuba", "Oboe", "Acoustic_Bass"));
    private String trackType;

    public enum TrackNumber {
        TRACK0, TRACK1, TRACK2, TRACK3, TRACK4, TRACK5, TRACK6, TRACK7, TRACKMAX
    };

    /**
    * Constructor for uMusicTrack 
    * @param trackNumber The TrackNumber assigned to this Track
    * @param type The track type - chord or melody
    * @param name The user defined name for this track
    * @param instrument The instrument to be played
    */
    uMusicTrack(TrackNumber trackNumber, String type, String name, String instrument) {
        this(trackNumber, type, name);
        this.instrument = instrument;
    }

    /**
    * Constructor for uMusicTrack 
    * @param trackNumber The TrackNumber assigned to this Track
    * @param type The track type - chord or melody
    * @param name The user defined name for this track
    */
    public uMusicTrack(TrackNumber trackNumber, String type, String name) {
        this.trackNumber = trackNumber;
        this.trackType = type;
        this.trackName = name;
    }

    /**
    * @param trackName The user defined name for this track
    */
    public void setName(String trackName){
        this.trackName = trackName;
    }

    /**
    * @param volume The desired volume for this track
    */
    public int setVolume(int volume) {
        if (volume > 125 || volume < 0) {
            return -1;
        }

        this.volume = volume;
        return 0;
    }

    /**
    * @param i The instrument to be played
    */
    public int setInstrument(String i) {
        if (InstrumentList.contains(i) == false) {
            System.out.println("The instrument is not valid: " + i);
            return -1;
        }

        this.instrument = i;
        return 0;
    }

    /**
    * @param note add the provided uMusicNote to the list for this track 
    */
    public int addNextNote(uMusicNote note) {
        notes.add(note);
        return 0;
    }

    /**
    * @param arrayIndex The index into the array
    * @param note replace the current note at arrayIndex with the note provided 
    */
    public void editNote(int arrayIndex, uMusicNote note) {
        notes.set(arrayIndex, note);
    }

    /**
    * @param arrayIndex The index into the array
    */
    public void deleteNote(int arrayIndex) {
        notes.remove(arrayIndex);
    }

    /**
    *  Delete the last note that was added
    */
    public void deleteLastNote() {
        notes.remove(notes.size() - 1);
    }

    /**
    * @return String returns the name of the track 
    */
    public String getTrackName() {
        return trackName;
    }

    /**
    * @return TrackNumber returns the track number of this track 
    */
    public TrackNumber getTrackNumber() {
        return trackNumber;
    }

    /**
    * @return int returns the volume of this track 
    */
    public int getVolume() {
        return volume;
    }

    /**
    * @return String returns the instrument for this track 
    */
    public String getInstrument() {
        return instrument;
    }

    /**
    * @return String returns the track type for this track 
    */
    public String getType() {
        return trackType;
    }

    /**
    * @return ArrayList returns a list of all of the notes for this track 
    */
    public ArrayList<uMusicNote> getTrackNotes() {
        return (ArrayList<uMusicNote>) notes.clone();
    }

    /**
    * @return ArrayList returns a list of all of the supported instruments for this track 
    */
    public ArrayList<String> getSupportedInstruments() {
        return (ArrayList<String>) InstrumentList.clone();
    }

    /**
    * @return String returns a jFugue formated string representation of this 
    * track with all of the notes 
    */
    public String buildTrackString() {
        String trackString;
        trackString = " V" + trackNumber.ordinal();
        trackString += " :CON(7," + volume + ")";
        trackString += " I" + MidiDictionary.INSTRUMENT_STRING_TO_BYTE.get(instrument.toUpperCase());

        Iterator<uMusicNote> iter = notes.iterator();
        while (iter.hasNext()) {
            uMusicNote next = iter.next();
                trackString += next.toString();  
        }

        return trackString;
    }
}
