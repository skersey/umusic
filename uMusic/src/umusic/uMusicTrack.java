package umusic;

import java.util.ArrayList;
import java.util.Iterator;
import org.jfugue.midi.MidiDictionary;
import umusic.uMusicNote.uMusicChord;
import umusic.uMusicNote.SharpFlat;

public class uMusicTrack {
	private int volume = 100;
	private String instrument = "Piano";	
	private String trackName;
	private String trackString;
	private TrackNumber trackNumber;
	private ArrayList <uMusicNote> notes = new ArrayList<>();

    uMusicTrack(TrackNumber trackNumber) {
        this(trackNumber, null);
    }

	//inversions

	public enum TrackNumber {TRACK0, TRACK1, TRACK2, TRACK3, TRACK4, TRACK5, TRACK6, TRACK7, TRACKMAX};
	
	public uMusicTrack (TrackNumber trackNumber, String name) {
		this.trackNumber = trackNumber;
		this.trackName = name;
	}

	public int setVolume (int volume) {
		if (volume > 125 || volume < 0)
			return -1;

		this.volume = volume;
		return 0;
	}
	
	public int setInstrument (String i) {
            switch (i) {
		case "Flute":
		case "Piano":
		case "Guitar":
		case "Violin":
		case "Tuba":
		case "Oboe":
		this.instrument = i;
			break;
		default:
		    System.out.println("The instrument is not valid: " + i );	
		    return -1;
            }

	    return 0;
	}

	public int addNextNote (uMusicNote n) {
		notes.add(n);
		return 0;
	}

	public void editNote (int arrayIndex, uMusicNote note) {
		notes.add(arrayIndex, note);
	}
	
	public void deleteLastNote () {
		notes.remove(notes.size()-1);
	}

	public ArrayList<uMusicNote> getTrackNotes() {
		return (ArrayList<uMusicNote>)notes.clone();
	}
	
	public String buildTrackString () {
		trackString  = " V" + trackNumber.ordinal();
		trackString += " :CON(7," + volume + ")";
		trackString += " I" + MidiDictionary.INSTRUMENT_STRING_TO_BYTE.get(instrument.toUpperCase());
		
		Iterator <uMusicNote> iter = notes.iterator();
		while (iter.hasNext()) {
			uMusicNote next = iter.next();
			trackString+=next.toString();
		}

		return trackString;
	}
}