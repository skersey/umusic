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
	
	public uMusicPercussionTrack (String name) {
		this.trackName = name;
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