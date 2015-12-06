package umusic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author bkersey
 */
public class uMusicRhythm {

    private int baseBeatDuration = 4; //1,2,4,8,16 where 2=1/2, 4=1/4, 8=1/8, 16=1/16
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

    public uMusicRhythm(String rhythmName) {
        this.rhythmName = rhythmName;
    }

    public uMusicRhythm(String rhythmName, int baseBeatDuration) {
        this(rhythmName);
        this.baseBeatDuration = baseBeatDuration;
    }

    public int getBaseBeatDuration() {
        return this.baseBeatDuration;
    }
    public String getRhythmName() {
        return rhythmName;
    }

    public void setRhythmName(String rhythmName) {
        this.rhythmName = rhythmName;
    }

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

    public String getRhythmLayer(Instrument instrument)  {
        return layerMap.get(instrument);
    }
            
    public void setRhythmLayer(Instrument instrument, String rhythm) {
        layerMap.put(instrument, rhythm);
    }


    @Override
    public String toString() {
        return toStaccatoString();
    }

}
