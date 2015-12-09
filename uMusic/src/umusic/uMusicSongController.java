package umusic;

import java.util.ArrayList;
import java.util.Arrays;
import javax.sound.midi.Sequence;
import org.jfugue.midi.MidiDictionary;
import org.jfugue.player.Player;
import umusic.uMusicTrack.TrackNumber;

/**
 *
 * @author bkersey
 */
public class uMusicSongController {

    private int masterVolume = 125;
    private int trackVolume = 125;
    private int timeSignatureNumerator = 4;
    private int timeSignatureDenominator = 4;
    private String tempo = "Allegro";
    private String name;
    private uMusicTrack[] trackList;
    private uMusicPercussionTrack percussionTrack;
    public ArrayList<String> tempoList = new ArrayList<>(Arrays.asList("Grave",
            "Largo", "Larghetto", "Lento", "Adagio", "Adagietto", "Andante",
            "Andantino", "Moderato", "Allegretto", "Allegro", "Vivace",
            "Presto", "Pretissimo"));

    public uMusicSongController(String name, String tempo, int timeSignatureNumerator, int timeSignatureDenominator, int masterVolume) {
        this();
        this.name = name;
        this.tempo = tempo;
        this.timeSignatureNumerator = timeSignatureNumerator;
        this.timeSignatureDenominator = timeSignatureDenominator;
        this.masterVolume = masterVolume;
    }

    public uMusicSongController(String name, String tempo, int timeSignatureNumerator, int timeSignatureDenominator) {
        this();
        this.name = name;
        this.tempo = tempo;
        this.timeSignatureNumerator = timeSignatureNumerator;
        this.timeSignatureDenominator = timeSignatureDenominator;
    }

    public uMusicSongController(String name, String tempo) {
        this();
        this.name = name;
        this.tempo = tempo;
    }

    public uMusicSongController(String name) {
        this();
        this.name = name;
    }

    public uMusicSongController() {
        trackList = new uMusicTrack[TrackNumber.TRACKMAX.ordinal()];
        for (int i = 0; i < TrackNumber.TRACKMAX.ordinal(); i++) {
            trackList[i] = null;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public int setMasterVolume(int volume) {
        if (volume > 125 || volume < 0) {
            return -1;
        }

        this.masterVolume = volume;
        setAllTrackVolume();
        return 0;
    }

    public void setAllTrackVolume(){

        for(int i = 0; i < trackList.length; i++){
            if(trackList[i] != null){
                setTrackVolume(trackList[i].getTrackNumber(), trackVolume);
            }
        }
    }
    public void setTimeSignature(int numerator, int denominator) {
        this.timeSignatureNumerator = numerator;
        this.timeSignatureDenominator = denominator;
    }

    public void setTimeSignature(String signature){
        String[] signatureArray = signature.split("/");
        this.timeSignatureNumerator = Integer.parseInt(signatureArray[0]);
        this.timeSignatureDenominator = Integer.parseInt(signatureArray[1]);
        
    }
    public int setTempo(String t) {
        if (tempoList.contains(t) == false) {
            System.out.println("The tempo name is not valid: " + t);
            return -1;
        }

        this.tempo = t;
        return 0;
    }

    public TrackNumber addTrack(String trackType, String trackName, String instrument) {
        for (int i = 0; i < TrackNumber.TRACKMAX.ordinal(); i++) {
            if (trackList[i] == null) {
                TrackNumber tn = TrackNumber.values()[i];
                trackList[i] = new uMusicTrack(tn, trackType, trackName, instrument);

                return tn;
            }
        }

        return TrackNumber.TRACKMAX;
    }

    @Deprecated
    public TrackNumber addTrack(String trackName) {
        for (int i = 0; i < TrackNumber.TRACKMAX.ordinal(); i++) {
            if (trackList[i] == null) {
                TrackNumber tn = TrackNumber.values()[i];
                trackList[i] = new uMusicTrack(tn, "Melody", trackName);

                return tn;
            }
        }

        return TrackNumber.TRACKMAX;
    }

    public void deleteTrack(TrackNumber trackNumber) {
        trackList[trackNumber.ordinal()] = null;
    }

    public void deleteAllTracks() {
        for (int i = 0; i < TrackNumber.TRACKMAX.ordinal(); i++) {
            trackList[i] = null;
        }

        percussionTrack = null;
    }

    public void addNoteToTrack(TrackNumber trackNumber, uMusicNote note) {
        trackList[trackNumber.ordinal()].addNextNote(note);
    }

    public void editNote(TrackNumber trackNumber, int arrayIndex, uMusicNote note) {
        trackList[trackNumber.ordinal()].editNote(arrayIndex, note);
    }

    public void deleteNote(TrackNumber trackNumber, int arrayIndex) {
        trackList[trackNumber.ordinal()].deleteNote(arrayIndex);
    }

    public void deleteLastNote(TrackNumber trackNumber) {
        trackList[trackNumber.ordinal()].deleteLastNote();
    }

    public int setTrackVolume(TrackNumber trackNumber, int volume) {
        int v;
        trackVolume = volume;
        if (volume == 0 || masterVolume == 0) {
            v = 0;
        } else {
            v = (volume * masterVolume) / 125;
        }
        System.out.println(v);
        return trackList[trackNumber.ordinal()].setVolume(v);
    }

    public int getTrackVolume(TrackNumber trackNumber) {
        return trackList[trackNumber.ordinal()].getVolume();
    }
    
    public int setInstrument(TrackNumber trackNumber, String i) {
        return trackList[trackNumber.ordinal()].setInstrument(i);
    }

    public ArrayList<uMusicNote> getTrackNotes(TrackNumber trackNumber) {
        return trackList[trackNumber.ordinal()].getTrackNotes();
    }

    public uMusicTrack[] getTracks() {
        return this.trackList;
    }

    public uMusicPercussionTrack getPercussionTrack() {
        return percussionTrack;
    }
    
    //percussion tracks
    public void addPercussionTrack(String trackName) {
        percussionTrack = new uMusicPercussionTrack(trackName);
    }

    public void deletePercussionTrack() {
        percussionTrack = null;
    }

//    public void addRhythmToTrack(uMusicRhythm rhythm) {
//        percussionTrack.addNextRhythm(rhythm);
//    }
//
//    public void editRhythm(int arrayIndex, uMusicRhythm rhythm) {
//        percussionTrack.editRhythm(arrayIndex, rhythm);
//    }
//
//    public void deleteLastRhythm() {
//        percussionTrack.deleteLastRhythm();
//    }

    public int setPercussionTrackVolume(int volume) {
        int v;

        if (volume == 0 || masterVolume == 0) {
            v = 0;
        } else {
            v = (volume * masterVolume) / 125;
        }

        percussionTrack.setVolume(v);
        return v;
    }

//    public ArrayList<uMusicRhythm> getTrackRhythms() {
//        return percussionTrack.getTrackRhythms();
//    }

    public ArrayList<String> getSupportedTempos() {
        return (ArrayList<String>) tempoList.clone();
    }

    public Sequence getSongSequence() {
        String song = "";
        song += " TIME:" + timeSignatureNumerator + "/" + timeSignatureDenominator;
        song += " T" + MidiDictionary.TEMPO_STRING_TO_INT.get(tempo.toUpperCase());

        for (int i = 0; i < TrackNumber.TRACKMAX.ordinal(); i++) {
            if (trackList[i] != null) {
                song += trackList[i].buildTrackString();
            }
        }

        if (percussionTrack != null) {
            song += percussionTrack.toStaccatoString();
        }

        System.out.println(song);
        return new Player().getSequence(song);
    }

    public Sequence getTrackSequence(TrackNumber trackNumber) {
        String song = "";
        song += " TIME:" + timeSignatureNumerator + "/" + timeSignatureDenominator;
        song += " T" + MidiDictionary.TEMPO_STRING_TO_INT.get(tempo.toUpperCase());

        if (trackList[trackNumber.ordinal()] != null) {
            song += trackList[trackNumber.ordinal()].buildTrackString();
        }

        System.out.println(song);
        return new Player().getSequence(song);
    }

    public Sequence getPercussionTrackSequence() {
        String song = "";
        song += " TIME:" + timeSignatureNumerator + "/" + timeSignatureDenominator;
        song += " T" + MidiDictionary.TEMPO_STRING_TO_INT.get(tempo.toUpperCase());

        if (percussionTrack != null) {
            song += percussionTrack.toStaccatoString();
        }

        System.out.println(song);
        return new Player().getSequence(song);
    }
    
    public int getTimeSignatureNumerator() {
        return timeSignatureNumerator;
    }

    public int getTimeSignatureDenominator() {
        return timeSignatureDenominator;
    }

    public String getTimeSignatureString() {
        return timeSignatureNumerator + "/" + timeSignatureDenominator;
    }

    String getTempo() {
        return tempo;
    }

    String getName() {
        return name;
    }

    uMusicTrack getTrack(TrackNumber trackNumber) {
        return trackList[trackNumber.ordinal()];
    }

}
