package umusic;

public class uMusicNote {
	private int duration = 4; //1,2,4,8, 16 where 2=1/2, 4=1/4, 8=1/8, 16=1/16
	private int octave = 5;   //range is 0-10
	private SharpFlat sharpFlat = SharpFlat.NONE;
	private uMusicChord chord = uMusicChord.NONE;
	private String note = "E";
	private String noteString;

	public enum SharpFlat {NONE, SHARP, DOUBLE_SHARP, FLAT, DOUBLE_FLAT} 
	public enum uMusicChord {NONE, MAJOR, MINOR} //Add other chords 
	
	public uMusicNote (String note) {
		this.note = note;	
	}

	public int setNote (String note) {
		this.note = note;
		return 0;
	}
	
	public int setDuration (int duration) {
		this.duration = duration; //check for errors
		return 0;
	}
	
	public int setOctave (int octave) {
		if (octave < 0 || octave > 10)
			return -1;

		this.octave = octave;
		return 0;
	}

	public void setSharpFlat (SharpFlat sharpFlat) {
		this.sharpFlat = sharpFlat;
	}
	
	public void setChord (uMusicChord chord) {
		this.chord = chord;
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

		switch (chord) {
			case MAJOR:
				noteString += "maj";
				break;
			case MINOR:
				noteString += "min";
				break; 
		}

		if (chord != uMusicChord.NONE)
			return;
		
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
}
