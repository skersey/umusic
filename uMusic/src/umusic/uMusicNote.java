package umusic;

import org.jfugue.pattern.Pattern;
import org.staccato.StaccatoParser;

/**
 * @author bkersey
 * 
 * This class contains all of the information necessary to define a note or chord.  
 */
public class uMusicNote {
	private int duration = 4; //1,2,4,8, 16 where 2=1/2, 4=1/4, 8=1/8, 16=1/16
	private int octave = 5;   //range is 0-10
	private boolean dotted = false;
	private SharpFlat sharpFlat = SharpFlat.NONE;
	private uMusicChord chord = uMusicChord.MELODY;
	private Inversion inversion = Inversion.NONE;
	private String note = "A";
	private String noteString;
	private String chordString;
	private String chordLessString;
	
	public enum SharpFlat {NONE, SHARP, DOUBLE_SHARP, FLAT, DOUBLE_FLAT} 
	public enum Inversion {NONE, SINGLE, DOUBLE} 
	public enum uMusicChord {
		AUGMENTED("aug"), DIMINISHED("dim"), DIMINISHED7("dim7"), 
		DOMINANT9("dom9"), DOMINANT11("dom11"), DOMINANT13("dom13"),
		MAJOR("maj"), MINOR("min"), MAJOR6("maj6"), MINOR6("min6"),
		MAJOR7("maj7"), MINOR7("min7"), MAJOR9("maj9"), MINOR9("min9"), 
		MAJOR13("maj13"), MINOR13("min13"), SUSPENDED2("sus2"), 
		SUSPENDED4("sus4"), NONE ("none"), MELODY ("melody"); 

		private final String jfugue;
    		uMusicChord(final String jfugue) {
        		this.jfugue = jfugue;
    		}

		/**
		 * @return String This returns a representation of the chord
		 * that jFugue accepts
		*/
		public String toJfugueString() {
			return this.jfugue;
		}

		/**
		 * @param value
		 * @return uMusicChord Returns the uMusicChord for the String provided 
		*/
		public uMusicChord getChordFromString(String value) {
    			uMusicChord c = null;
    			uMusicChord chords[] = values();
    		
			for (uMusicChord chord1 : chords) {
				if (chord1.name().equals(value)) {
					c = chord1;
					break;
				}
			}
			return c;
		}
	}
	
	/**
	 * Constructor for melody notes 
	 * @param note This is the note
	 * @param dur This is the duration for the note   
	 * @param octave This is the octave for the note
	 * @param sf Defines if the note is Sharp or Flat
	 * @param dotted Defines if the note is dotted
	*/
	public uMusicNote (String note, int dur, int octave, SharpFlat sf, boolean dotted) {
		this.note = note;	
		this.duration = dur; 
		this.octave = octave;
		this.sharpFlat = sf;
		this.dotted = dotted;
	}
	
	/**
	 *  Constructor for chords 
	 * @param note This is the base note for the chord
	 * @param dur This is the duration for the chord notes   
	 * @param octave This is the octave for the chord notes
	 * @param sf Defines if the chord notes are Sharp or Flat
	 * @param chord The uMusicChord for this chord 
	 * @param inv Defines if the chord has one or two inversions
	 * @param dotted Defines if the chord notes are dotted
	*/
	public uMusicNote (String note, int dur, int octave, SharpFlat sf, uMusicChord chord, Inversion inv, boolean dotted) {
		this.note = note;	
		this.duration = dur; 
		this.octave = octave;
		this.sharpFlat = sf;
		this.chord = chord;
		this.inversion = inv;
		this.dotted = dotted;
	}
	
	/**
	*    Constructor to copy a uMusicNote  
	*/
	public uMusicNote (uMusicNote copy) {
		this.note = copy.note;	
		this.duration = copy.duration; 
		this.octave = copy.octave;
		this.sharpFlat = copy.sharpFlat;
		this.chord = copy.chord;
		this.inversion = copy.inversion;
		this.dotted = copy.dotted;
	}
	
	/**
	*    Build a string in jFugue format that represents this note/chord  
	*/
	private void buildNoteString() {
		//The jfugue representation of this note must be in the following
		//order.  Do not change the order that the string is contrusted in
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

		if (chord != uMusicChord.MELODY) {
			chordLessString = noteString;
			if (note.toUpperCase().equals("R") == false){
                            if(!chord.toJfugueString().contains("none")){
                                noteString += chord.toJfugueString();
                                chordString = chord.toJfugueString();

                                switch(inversion) {
                                    case SINGLE:	
                                            if (note.toUpperCase().equals("R") == false)
                                                    noteString += "^";
                                            chordString += "^";
                                            break;
                                    case DOUBLE:	
                                            if (note.toUpperCase().equals("R") == false)
                                                    noteString += "^^";
                                            chordString += "^^";
                                            break;
                                }
                            }
                        }
		} 
                
		switch (duration) {
			case 1:
				noteString += "w"; 
				chordLessString += "w"; 
			    break;
			case 2:
				noteString += "h"; 
				chordLessString += "h"; 
			    break;
			case 4:
				noteString += "q"; 
				chordLessString += "q"; 
			    break;
			case 8:
				noteString += "i"; 
				chordLessString += "i"; 
			    break;
			case 16:
				noteString += "s"; 
				chordLessString += "s"; 
			    break;
		}

		if (dotted) {
			noteString += "."; 
			chordLessString += "."; 
		}            
	}
	
	/**
	* @return String  Returns a string that contains the jfugue formatted representation
	* of this note/chord
	*/
	@Override
	public String toString() {
	    	buildNoteString();
		chordLessString = "";
		chordString = "";
		return noteString;
	}

	/**
	* @return String  Returns a string that only contains the chord specific information
	*/
	public String getChordString() {
	    	buildNoteString();
		return chordString;
	}

	/**
	* @return String  Returns a string that omits the chord specific information
	*/
	public String getChordLessString() {
	    	buildNoteString();
		return chordLessString;
	}

}
