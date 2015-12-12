package umusic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bkersey
 * 
 * This class contains all of the information necessary to define a music track.  
 */
public class uMusicPercussionTrack {

    private int volume = 125;
    private String trackName;
    private final RhythmBank rhythmBank = new RhythmBank();
    private final List<Integer> trackSequence = new ArrayList<>();
    private final Integer trackNumber = 9;

    public uMusicPercussionTrack(String name) {
        this.trackName = name;
    }

    public uMusicPercussionTrack(String name, int volume) {
        this(name);
        this.volume = volume;
    }

    /**
    * @param volume The desired volume for this track
    */
    public void setVolume(int volume) {
        if (volume > 125 || volume < 0) {
            throw new IllegalArgumentException();
        }

        this.volume = volume;
    }

    /**
    * @param rhythmId The name of the rhythmbank to remove 
    */
    public void removeRhythm(Integer rhythmId) {
        this.rhythmBank.remove(rhythmId);
        this.trackSequence.removeIf(elem->elem==rhythmId);
    }
   
    
    /**
    * @return RhythmBank returns the RhythmBank for this track 
    */
    public RhythmBank getRhythmBank() {
        return rhythmBank;
    }

    /**
    * @return List<Integer> returns the trackSequence for this track 
    */
    public List<Integer> getTrackSequence() {
        return trackSequence;
    }

    /**
    * @return String returns the name of the track 
    */
    public String getTrackName() {
        return trackName;
    }

    /**
    * @param trackName The user defined name for this track
    */
    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    /**
    * @return String returns a jfugue formated string representation of this 
    * track with all of the notes 
    */
    public String toStaccatoString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" V").append(trackNumber);
        sb.append(" :CON(7," + volume + ")");
        for (Integer rhythmKey : trackSequence) {
            sb.append(rhythmBank.get(rhythmKey).toStaccatoString());
        }

        return sb.toString();
    }

}
