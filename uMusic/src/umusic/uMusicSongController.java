package umusic;

import java.util.ArrayList;
import javax.sound.midi.Sequence;
import org.jfugue.midi.MidiDictionary;
import org.jfugue.player.Player;
import umusic.uMusicTrack.TrackNumber;

public class uMusicSongController {

    private int masterVolume;
    private int timeSignatureNumerator = 4;
    private int timeSignatureDenominator = 4;
    private String tempo= "Allegro";
    private String name;
    private uMusicTrack[] trackList;
    
	
        public uMusicSongController() {
            trackList = new uMusicTrack[TrackNumber.TRACKMAX.ordinal()];
        }
	
        public void setName(String name) {
     	    this.name = name;
        }
   
        public int setMasterVolume(int volume) {
	    if (volume > 125 || volume < 0)
                return -1;

	    this.masterVolume = volume;
	    return 0;
        }
   
        public void setTimeSignature(int numerator, int denominator) {
	    this.timeSignatureNumerator = numerator;	//add check for errors
	    this.timeSignatureDenominator = denominator;	//add check for errors
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

	
        public void addTrack(TrackNumber trackNumber, String trackName) {
            trackList[trackNumber.ordinal()] = new uMusicTrack(trackNumber, trackName);
        }
   
        public void addNoteToTrack(TrackNumber trackNumber, uMusicNote note) {
	    trackList[trackNumber.ordinal()].addNextNote(note);
        }
   
        public void editNote(TrackNumber trackNumber, int arrayIndex, uMusicNote note) {
	    trackList[trackNumber.ordinal()].editNote(arrayIndex, note);
        }

        public void deleteLastNote(TrackNumber trackNumber) {
	    trackList[trackNumber.ordinal()].deleteLastNote();
        }
   
        public int setTrackVolume(TrackNumber trackNumber, int volume) {
	    int v;
	
	    if (volume == 0 || masterVolume == 0)
                v=0;
            else	
	        v = (volume * masterVolume) / 125;

	    return trackList[trackNumber.ordinal()].setVolume(v);
        }
   
        public int setInstrument(TrackNumber trackNumber, String i) {
	    return trackList[trackNumber.ordinal()].setInstrument(i);
        }
   
        public ArrayList<uMusicNote> getTrackNotes(TrackNumber trackNumber) {
	    return trackList[trackNumber.ordinal()].getTrackNotes(); 
        }

        public Sequence getSongSequence() {
	    String song = "";
	    song += " TIME:" + timeSignatureNumerator + "/" + timeSignatureDenominator;
            song += " T" + MidiDictionary.TEMPO_STRING_TO_INT.get(tempo.toUpperCase());
	
	    for (int i = 0; i < TrackNumber.TRACKMAX.ordinal(); i++) {
		if (trackList[i] != null)
                    song += trackList[i].buildTrackString();
	    }
	
	    System.out.println (song);
 	    return new Player().getSequence(song);
        }
}
