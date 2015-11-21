package umusic;

import org.jfugue.integration.LilyPondParserListener;
import org.jfugue.pattern.Pattern;
import org.staccato.StaccatoParser;

public class uMusicNote {
	private int duration = 4; //1,2,4,8, 16 where 2=1/2, 4=1/4, 8=1/8, 16=1/16
	private int octave = 5;   //range is 0-10
	private SharpFlat sharpFlat = SharpFlat.NONE;
	private uMusicChord chord = uMusicChord.NONE;
	private Inversion inversion = Inversion.NONE;
	private String note = "A";
	private String noteString;

	private StaccatoParser parser = null;
	private LilyPondParserListener listener = null;
	private Pattern pattern = null;
	
	public enum SharpFlat {NONE, SHARP, DOUBLE_SHARP, FLAT, DOUBLE_FLAT} 
	public enum Inversion {NONE, SINGLE, DOUBLE} 
	public enum uMusicChord {NONE, MAJOR, MINOR, MAJOR7, MINOR7, MAJOR9, MINOR9} //Add other chords 
	
	public uMusicNote (String note, int dur, int octave, SharpFlat sf, uMusicChord c) {
		this.note = note;	
		this.duration = dur; 
		this.octave = octave;
		this.sharpFlat = sf;
		this.chord = c;
	}
	
	public uMusicNote (String note, int dur, int octave, SharpFlat sf, uMusicChord c, Inversion i) {
		this.note = note;	
		this.duration = dur; 
		this.octave = octave;
		this.sharpFlat = sf;
		this.chord = c;
		this.inversion = i;
	}
	
	public uMusicNote (uMusicNote copy) {
		this.note = copy.note;	
		this.duration = copy.duration; 
		this.octave = copy.octave;
		this.sharpFlat = copy.sharpFlat;
		this.chord = copy.chord;
		this.inversion = copy.inversion;
	}
	
	private void buildNoteString() {
		noteString = " " + note.toUpperCase();
		
		switch (sharpFlat) {
			case SHARP:  
				noteString += "#"; 
				break;	
			case DOUBLE_SHARP:  
				noteString += "##"; 
				break;	
			case FLAT:  
				noteString += "b"; 
				break;	
			case DOUBLE_FLAT:  
				noteString += "bb"; 
				break;	
		}
		
		if (note.toUpperCase().equals("R") == false) 
			noteString += octave;

		if (chord != uMusicChord.NONE) {
			switch (chord) {
				case MAJOR:
					noteString += "maj";
					break;
				case MINOR:
					noteString += "min";
					break; 
				case MAJOR7:
					noteString += "maj7";
					break;
				case MINOR7:
					noteString += "min7";
					break; 
				case MAJOR9:
					noteString += "maj9";
					break;
				case MINOR9:
					noteString += "min9";
					break; 
			}

			switch(inversion) {
				case SINGLE:	
					noteString += "^";
					break;
				case DOUBLE:	
					noteString += "^^";
					break;
			}
		}

		switch (duration) {
			case 1:
				noteString += "w"; 
			    break;
			case 2:
				noteString += "h"; 
			    break;
			case 4:
				noteString += "q"; 
			    break;
			case 8:
				noteString += "i"; 
			    break;
			case 16:
				noteString += "s"; 
			    break;
		}
	}
	
	@Override
	public String toString() {
	    	buildNoteString();
		return noteString;
	}

	public String toLilyPond() {
		parser = new StaccatoParser();
		listener = new LilyPondParserListener();
		parser.addParserListener(listener);

		pattern = new Pattern();
		pattern = new Pattern(this.toString());
		parser.parse(pattern);
		return listener.getLyString();
	}
}
