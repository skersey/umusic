package umusic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author bkersey
 * 
 * This class contains all of the information necessary to define a rhythm.  
 */
public class uMusicRhythm {

    private int baseBeatDuration = 4; 
    private String rhythmName;
    private final Map<Instrument, String> layerMap = new ConcurrentHashMap<>();

    public enum Instrument {
        BASS("BASS_DRUM"), SNARE("ACOUSTIC_SNARE"), CLOSED_HI_HAT("CLOSED_HI_HAT"), OPEN_HI_HAT("OPEN_HI_HAT"), CYMBAL("CRASH_CYMBAL_1");
        private String midiName;

        private Instrument(String midiName) {
            this.midiName = midiName;
        }

        public String getMidiName() {
            return midiName;
        }
    };

    /**
    * Constructor for uMusicRhythm 
    * @param rhythmName The user defined name for this rhythm 
    */
    public uMusicRhythm(String rhythmName) {
        this.rhythmName = rhythmName;
    }

    /**
    * Constructor for uMusicRhythm 
    * @param rhythmName The user defined name for this rhythm 
    * @param baseBeatDuration the duration for the beat
    */
    public uMusicRhythm(String rhythmName, int baseBeatDuration) {
        this(rhythmName);
        this.baseBeatDuration = baseBeatDuration;
    }

    /**
    * @return int returns the baseBeatDuration for this rhythm 
    */
    public int getBaseBeatDuration() {
        return this.baseBeatDuration;
    }
    
    /**
    * @return String returns the name of the rhythm 
    */
    public String getRhythmName() {
        return rhythmName;
    }

    /**
    * @param rhythmName The user defined name for this rhythm 
    */
    public void setRhythmName(String rhythmName) {
        this.rhythmName = rhythmName;
    }

    /**
    * @return String returns a jfugue formated string representation of this 
    * rhythm with all of the notes and Layers
    */
    public String toStaccatoString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        int currLayer = 1;
        char beatChar = getBaseBeatChar();
        for (Map.Entry<Instrument, String> entrySet : layerMap.entrySet()) {
            String layerString = entrySet.getValue();
            Instrument instrument = entrySet.getKey();
            sb.append(" L").append(currLayer++);
            for (int i = 0; i < layerString.length(); i++) {
                if (layerString.charAt(i) == '.') {
                    sb.append(" R");
                } else {
                    sb.append(" [").append(instrument.getMidiName()).append("]");
                }
                sb.append(beatChar);
            }
        }

        return sb.toString();
    }

    /**
     * @return char returns a char representation of the baseBeatDuration for this rhythm
     * @return 
     */
    private char getBaseBeatChar() {
        switch (baseBeatDuration) {
            case 1:
                return 'w';
            case 2:
                return 'h';
            case 4:
                return 'q';
            case 8:
                return 'i';
            case 16:
                return 's';
            default:
                throw new IllegalArgumentException("Invalid base beat duration: " + baseBeatDuration);
        }
    }

    /**
    * @param instrument
    * @return String returns the rhythm associated with the instrument  
    */
    public String getRhythmLayer(Instrument instrument)  {
        return layerMap.get(instrument);
    }
            
    /**
    * this method associates the provided instrument and rhythm into the rhythm layerMap
    * @param instrument
    * @param rhythm   
    */
    public void setRhythmLayer(Instrument instrument, String rhythm) {
        layerMap.put(instrument, rhythm);
    }


    /**
    * @return String returns a jfugue formated string representation of this 
    * rhythm with all of the notes and Layers
    */
    @Override
    public String toString() {
        return toStaccatoString();
    }
}
