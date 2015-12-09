package umusic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bkersey
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

    public void setVolume(int volume) {
        if (volume > 125 || volume < 0) {
            throw new IllegalArgumentException();
        }

        this.volume = volume;

    }

    public void removeRhythm(Integer rhythmId) {
        this.rhythmBank.remove(rhythmId);
        this.trackSequence.removeIf(elem->elem==rhythmId);
    }
    public RhythmBank getRhythmBank() {
        return rhythmBank;
    }

    public List<Integer> getTrackSequence() {
        return trackSequence;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

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
