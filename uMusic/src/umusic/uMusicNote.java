package umusic;

import org.jfugue.integration.LilyPondParserListener;
import org.jfugue.pattern.Pattern;
import org.staccato.StaccatoParser;

/**
 *
 * @author bkersey
 */
public class uMusicNote {
	private int duration = 4; //1,2,4,8, 16 where 2=1/2, 4=1/4, 8=1/8, 16=1/16
	private int octave = 5;   //range is 0-10
	private boolean dotted = false;
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
	public enum uMusicChord {
		NONE ("none"), AUGMENTED("aug"), DIMINISHED("dim"), DIMINISHED7("dim7"), 
		DOMINANT9("dom9"), DOMINANT11("dom11"), DOMINANT13("dom13"),
		MAJOR("maj"), MINOR("min"), MAJOR6("maj6"), MINOR6("min6"),
		MAJOR7("maj7"), MINOR7("min7"), MAJOR9("maj9"), MINOR9("min9"), 
		MAJOR13("maj13"), MINOR13("min13"), SUSPENDED2("sus2"), SUSPENDED4("sus4"); 

		private final String jfugue;
    		uMusicChord(final String jfugue) {
        		this.jfugue = jfugue;
    		}

		public String toJfugueString() {
			return this.jfugue;
		}
	}
	
	public uMusicNote (String note, int dur, int octave, SharpFlat sf, boolean dotted) {
		this.note = note;	
		this.duration = dur; 
		this.octave = octave;
		this.sharpFlat = sf;
		this.dotted = dotted;
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
		this.dotted = copy.dotted;
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
			noteString += chord.toJfugueString();

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

		if (dotted)
			noteString += "."; 
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
