package umusic;

import java.util.ArrayList;
import java.util.Iterator;
import org.jfugue.midi.MidiDictionary;
import umusic.uMusicNote.uMusicChord;
import umusic.uMusicNote.SharpFlat;

public class uMusicTrack {
	private int volume = 100;
	private int timeSignatureNumerator = 4;
	private int timeSignatureDenominator = 4;
	private String instrument = "Piano";	
	private String tempo= "Allegro";
	private String trackString;
	private TrackNumber trackNumber;
	private ArrayList <uMusicNote> track = new ArrayList<>();

	//inversions

	public enum TrackNumber {TRACK0, TRACK1, TRACK2, TRACK3, TRACK4, TRACK5, TRACK6, TRACK7};
	
	public uMusicTrack (TrackNumber trackNumber) {
		this.trackNumber = trackNumber;
	}

	public int setVolume (int volume) {
		if (volume > 125 || volume < 15)
			return -1;

		this.volume = volume;
		return 0;
	}
	
	public int setTimeSignature (int numerator, int denominator) {
		this.timeSignatureNumerator = numerator;	//add check for errors
		this.timeSignatureDenominator = denominator;	//add check for errors
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

	public int setTempo (String t) {
            switch (t) {
		case "Grave":
		case "Largo":
		case "Larghetto":
		case "Lento":
		case "Adagio":
		case "Adagietto":
		case "Andante":
		case "Andantino":
		case "Moderato":
		case "Allegretto":
		case "Allegro":
		case "Vivace":
		case "Presto":
		case "Pretissimo":
		this.tempo = t;
			break;
		default:
		    System.out.println("The tempo name is not valid: " + t );	
		    return -1;
            }

	    return 0;
	}

	public int addNextNote (String note, int duration, int octave, 
		SharpFlat sf, uMusicChord mm) {
		uMusicNote n = new uMusicNote(note);
		
		if (n.setDuration(duration) < 0)
			return -1;
		if (n.setOctave(octave) < 0)
			return -1;

		n.setSharpFlat(sf);
		n.setChord(mm);

		track.add(n);
		return 0;
	}

	public int editNote (int arrayIndex, String note, int duration, int octave) {
		uMusicNote n = track.get(arrayIndex);

		if (n.setNote(note) < 0)
			return -1;
		if (n.setDuration(duration) < 0)
			return -1;
		if (n.setOctave(octave) < 0)
			return -1;

		return 0;		
	}
	
	public void deleteLastNote () {
		track.remove(track.size()-1);
	}
	
	public String buildTrackString () {
		trackString  = "V" + trackNumber.ordinal();
		trackString += " :CON(7," + volume + ")";
		trackString += " TIME:" + timeSignatureNumerator + "/" + timeSignatureDenominator;
		trackString += " I" + MidiDictionary.INSTRUMENT_STRING_TO_BYTE.get(instrument.toUpperCase());
		trackString += " T" + MidiDictionary.TEMPO_STRING_TO_INT.get(tempo.toUpperCase());
		
		Iterator <uMusicNote> iter = track.iterator();
		while (iter.hasNext()) {
			uMusicNote next = iter.next();
			trackString+=next.toString();
		}

		return trackString;
	}
}