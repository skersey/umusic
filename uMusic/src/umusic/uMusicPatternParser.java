package umusic;

import org.jfugue.pattern.Pattern;
import umusic.uMusicNote.SharpFlat;
import umusic.uMusicNote.uMusicChord;

public class uMusicPatternParser {

	private String[] tracks;
	
	public uMusicPatternParser (Pattern p) {
		
		tracks = p.toString().split("V");	

		for (int i = 0; i < tracks.length; i++) {
			if (tracks[i].length()==0)
				continue;

			tracks[i] = "V"+tracks[i];
			System.out.println(tracks[i]);
		}
	}

	public int getNumTracks () {
		return tracks.length - 1;
	}

	public uMusicTrack parseTrack (uMusicTrack.TrackNumber trackNumber) {
		int i = 0;
		for (; i < tracks.length; i++) {
			if (tracks[i].startsWith("V"+trackNumber.ordinal()))
				break;
		}

		uMusicTrack t = new uMusicTrack(trackNumber);
		String[] staccato = tracks[i].split(" ");

		for (int l = 0; l < staccato.length; l++) {
			//discard midi timimg information
			if (staccato[l].startsWith("@"))
				continue;
			
			System.out.println(staccato[l]);

			//Voice	
			if (staccato[l].startsWith("V")) //already set the voice
				continue;

			//Volume:  format= :CON(7,80) 
			if (staccato[l].startsWith(":CON")) {
				int left  = staccato[l].indexOf(",");
				int right = staccato[l].indexOf(")");
				String volume = staccato[l].substring(left+1, right);

				System.out.println("Volume is " + volume);
				continue;
			}

			//Time: format= TIME:4/4 
			if (staccato[l].startsWith("TIME:")) {
				String time = staccato[l].substring(5);
				String[] ts = time.split("/");
				System.out.println("time is " + ts[0] + " " + ts[1]);
				
				continue;
			}
		
			//Instrument: format= I53
			if (staccato[l].startsWith("I")) {
				String instrument = staccato[l].substring(1);
				System.out.println("Instrument is " + instrument);
				continue;
			}
				
			//Tempo: format= T120 
			if (staccato[l].startsWith("T")) { 
				String tempo = staccato[l].substring(1);
				System.out.println("Temp is " + tempo);
				continue;
			}
			
			//if here, then it is a music note
			parseMusicNote (staccato[l], t);
		}
		return t;
	}

	private void parseMusicNote (String s, uMusicTrack t) {
		String note = null;
		String chord = "";
		char duration = 'g';
		int octave = 5;
		uMusicNote.SharpFlat sf = SharpFlat.NONE;
		uMusicNote.uMusicChord c = uMusicChord.NONE;
			
		//Rest	
/*		if (s.startsWith("R")) { 
			duration = Integer.parseInt(s.substring(1));
			t.addNextNote("R", duration, 0, SharpFlat.NONE, uMusicChord.NONE);
			System.out.println("Rest with Duration " + duration);
			return;
		}
*/
		String cur;
		
		//the first character is always the note
		note = s.substring(0, 1);
		
		//the second character is always the octave
		for (int i = 1; i < s.length(); i++) {
			cur = s.substring(i);

			if (cur.startsWith("bb")) {
				sf = SharpFlat.DOUBLE_FLAT;
				i++;
				continue;
			}

			if (cur.startsWith("b")) {
				sf = SharpFlat.FLAT;
				continue;
			}
		
			if (cur.startsWith("##")) {
				sf = SharpFlat.DOUBLE_SHARP;
				i++;
				continue;
			}

			if (cur.startsWith("#")) {
				sf = SharpFlat.SHARP;
				continue;
			}
		
		        if (cur.startsWith("w") || cur.startsWith("h") ||
				cur.startsWith("q") || cur.startsWith("i") 
				|| cur.startsWith("s"))	{
				duration = cur.charAt(0);
				continue;
			}

		        if (cur.startsWith("10")) {
				octave = 10;	
				i++;
				continue;
			}

		        if (cur.startsWith("0") || cur.startsWith("1") || cur.startsWith("2") ||
			    cur.startsWith("3") || cur.startsWith("4") || cur.startsWith("5") ||
			    cur.startsWith("6") || cur.startsWith("7") || cur.startsWith("8") ||
			    cur.startsWith("9")) {
				octave = Character.getNumericValue(cur.charAt(0));
				continue;
			}

			//if here, then it's a chord
			chord = cur;
			break;
			
		}

		System.out.println("The Note: " + note + sf + octave + chord + duration);
//		t.addNextNote(note, duration, octave, sf, c);
	}

	
}