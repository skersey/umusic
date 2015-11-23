/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umusic;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author bkersey
 */
public class uMusicPercussionTrack {

	private int volume = 100;
	private String trackString;
	private final String trackName;
	private final ArrayList <uMusicRhythm> rhythms = new ArrayList<>();
//	private final ArrayList <uMusicNote>[] notes = new ArrayList[PercussionInstrument.INSTRUMENT_MAX.ordinal()];
//	private final String[] instrumentNames = {"BASS_DRUM", "ACOUSTIC_SNARE", "CLOSED_HI_HAT", "OPEN_HI_HAT", "CRASH_CYMBAL_1"};

	//inversions

//	public enum PercussionInstrument {BASS, SNARE, CLOSED_HI_HAT, OPEN_HI_HAT, CYMBAL, INSTRUMENT_MAX};
	
	public uMusicPercussionTrack (String name) {
		this.trackName = name;
//		for (int i = 0; i < PercussionInstrument.INSTRUMENT_MAX.ordinal(); i++) 
//			notes[i] = new ArrayList<>();
	}

	public int setVolume (int volume) {
		if (volume > 125 || volume < 0)
			return -1;

		this.volume = volume;
		return 0;
	}

	public int addNextRhythm (uMusicRhythm r) {
		rhythms.add(r);
		return 0;
	}

	public void editRhythm (int arrayIndex, uMusicRhythm r) {
		rhythms.add(arrayIndex, r);
	}
	
	public void deleteLastRhythm () {
		rhythms.remove(rhythms.size()-1);
	}

	public ArrayList<uMusicRhythm> getTrackRhythms() {
		return (ArrayList<uMusicRhythm>)rhythms.clone();
	}

/*	
	public int addNextNote (PercussionInstrument i, uMusicNote n) {
		notes[i.ordinal()].add(n);
		return 0;
	}

	public void editNote (PercussionInstrument i, int arrayIndex, uMusicNote note) {
		notes[i.ordinal()].add(arrayIndex, note);
	}
	
	public void deleteLastNote (PercussionInstrument i) {
		notes[i.ordinal()].remove(notes[i.ordinal()].size()-1);
	}

	public ArrayList<uMusicNote> getTrackNotes(PercussionInstrument i) {
		return (ArrayList<uMusicNote>)notes[i.ordinal()].clone();
	}
*/	
	public String buildTrackString () {
		trackString  = " V9";
		trackString += " :CON(7," + volume + ")";

		
			Iterator <uMusicRhythm> iter = rhythms.iterator();
			while (iter.hasNext()) {
				uMusicRhythm next = iter.next();
				trackString+=next.toString();
			}

		return trackString;
	}
}