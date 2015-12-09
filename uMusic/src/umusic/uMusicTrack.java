package umusic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.jfugue.midi.MidiDictionary;

/**
 *
 * @author bkersey
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

    uMusicTrack(TrackNumber trackNumber, String type, String name, String instrument, int volume) {
        this(trackNumber, type, name, instrument);
        this.volume = volume;
    }

    uMusicTrack(TrackNumber trackNumber, String type, String name, String instrument) {
        this(trackNumber, type, name);
        this.instrument = instrument;
    }

    public uMusicTrack(TrackNumber trackNumber, String type, String name) {
        this.trackNumber = trackNumber;
        this.trackType = type;
        this.trackName = name;
    }

    public int setVolume(int volume) {
        if (volume > 125 || volume < 0) {
            return -1;
        }

        this.volume = volume;
        return 0;
    }

    public int setInstrument(String i) {
        if (InstrumentList.contains(i) == false) {
            System.out.println("The instrument is not valid: " + i);
            return -1;
        }

        this.instrument = i;
        return 0;
    }

    public int addNextNote(uMusicNote n) {
        notes.add(n);
        return 0;
    }

    public void editNote(int arrayIndex, uMusicNote note) {
        notes.set(arrayIndex, note);
    }

    public void deleteNote(int arrayIndex) {
        notes.remove(arrayIndex);
    }

    public void deleteLastNote() {
        notes.remove(notes.size() - 1);
    }

    public String getTrackName() {
        return trackName;
    }

    public TrackNumber getTrackNumber() {
        return trackNumber;
    }

    public int getVolume() {
        return volume;
    }

    public String getInstrument() {
        return instrument;
    }

    public String getType() {
        return trackType;
    }

    public ArrayList<uMusicNote> getTrackNotes() {
        return (ArrayList<uMusicNote>) notes.clone();
    }

    public ArrayList<String> getSupportedInstruments() {
        return (ArrayList<String>) InstrumentList.clone();
    }

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
