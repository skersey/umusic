package umusic;

/**
 *
 * @author bkersey
 */
public class uMusicRhythm {
	private int duration = 4; //1,2,4,8,16 where 2=1/2, 4=1/4, 8=1/8, 16=1/16
	private String rhythmName;
	private String rhythmString;
	private String[] layer = new String[PercussionInstrument.INSTRUMENT_MAX.ordinal()];
	private final String[] instrumentNames = {"BASS_DRUM", "ACOUSTIC_SNARE", "CLOSED_HI_HAT", "OPEN_HI_HAT", "CRASH_CYMBAL_1"};

	public enum PercussionInstrument {BASS, SNARE, CLOSED_HI_HAT, OPEN_HI_HAT, CYMBAL, INSTRUMENT_MAX};
	
	public uMusicRhythm (String name) {
		this.rhythmName = name;
		for (int i = 0; i < PercussionInstrument.INSTRUMENT_MAX.ordinal(); i++) 
			layer[i] = null;
	}
	
	public uMusicRhythm (uMusicRhythm copy) {
		this.duration = copy.duration;
		this.rhythmName = copy.rhythmName;
		this.rhythmString = copy.rhythmString;
		this.layer = copy.layer;
	}

	public void setRhythmLayer(PercussionInstrument instrument, String rhythm) {
		this.layer[instrument.ordinal()] = rhythm;
	}
	
	public void setRhythmDuration(int duration) {
		this.duration = duration;
	}

	private void buildRhythmString() {
		rhythmString = " ";

		for (int curLayer = 0; curLayer < PercussionInstrument.INSTRUMENT_MAX.ordinal(); curLayer++) {
			if (layer[curLayer] == null)
				continue;
			
			rhythmString  += " L" + (curLayer+1);
			
			char[] r = layer[curLayer].toCharArray();

			for (int j = 0; j < r.length; j++) {
				rhythmString += " ";
				
				if (r[j] == '.') {
					rhythmString += "R"; 
				} else {
					rhythmString += "[" + instrumentNames[curLayer] + "]"; 
				}
		
				switch (duration) {
					case 1:
						rhythmString += "w"; 
			 			 break;
					case 2:
						rhythmString += "h"; 
			    			break;
					case 4:
						rhythmString += "q"; 
			    			break;
					case 8:
						rhythmString += "i"; 
			    			break;
					case 16:
						rhythmString += "s"; 
			    			break;
				}
			}
		}
	}
	
	@Override
	public String toString() {
	    	buildRhythmString();
		return rhythmString;
	}

}
