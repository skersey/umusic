package umusic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.ManagedPlayer;
import org.jfugue.player.Player;
import umusic.uMusicNote.uMusicChord;
import umusic.uMusicNote.SharpFlat;
import umusic.uMusicTrack.TrackNumber;

public class UMusic {
	static ArrayList <uMusicNote> voice1 = new ArrayList<>();

	static uMusicTrack track1;
	static uMusicTrack track2;
	static uMusicTrack track3;

	public static void makeTrack1 () {
		track1 = new uMusicTrack (TrackNumber.TRACK1);

		track1.setInstrument("Flute");
		track1.setTempo("Allegro");
		track1.setTimeSignature(4, 4);
		track1.setVolume(100);

		track1.addNextNote("c", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("g", 1, 5, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("e", 2, 5, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("f", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("a", 4, 5, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("b", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("r", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("c", 16, 5, SharpFlat.NONE, uMusicChord.MINOR);
		track1.addNextNote("c", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track1.addNextNote("c", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track1.addNextNote("f", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track1.addNextNote("g", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track1.addNextNote("c", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track1.addNextNote("f", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track1.addNextNote("g", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track1.addNextNote("c", 8, 3, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("g", 1, 3, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("e", 2, 3, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("f", 8, 3, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("a", 4, 7, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("b", 8, 7, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("r", 8, 7, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("c", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("g", 1, 5, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("e", 2, 5, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("f", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("a", 4, 5, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("b", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("r", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("c", 16, 5, SharpFlat.DOUBLE_FLAT, uMusicChord.MINOR);
		track1.addNextNote("c", 8, 5, SharpFlat.DOUBLE_FLAT, uMusicChord.MAJOR);
		track1.addNextNote("c", 8, 5, SharpFlat.DOUBLE_FLAT, uMusicChord.MAJOR);
		track1.addNextNote("f", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track1.addNextNote("g", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track1.addNextNote("c", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track1.addNextNote("f", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track1.addNextNote("g", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track1.addNextNote("c", 8, 3, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("g", 1, 3, SharpFlat.NONE, uMusicChord.NONE);
		track1.addNextNote("e", 2, 3, SharpFlat.SHARP, uMusicChord.NONE);
		track1.addNextNote("f", 8, 3, SharpFlat.SHARP, uMusicChord.NONE);
		track1.addNextNote("a", 4, 7, SharpFlat.SHARP, uMusicChord.NONE);
		track1.addNextNote("b", 8, 7, SharpFlat.SHARP, uMusicChord.NONE);
		
		track1.deleteLastNote(); // delete the last note added

		track1.editNote(0, "a", 8, 5); //edit the note at given array position
	}

	public static void makeTrack2 () {
		track2 = new uMusicTrack (TrackNumber.TRACK2);

		track2.setInstrument("Piano");
		track2.setTempo("Allegro");
		track2.setTimeSignature(4, 4);
		track2.setVolume(80);

		track2.addNextNote("c", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("g", 1, 5, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("e", 2, 5, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("f", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("a", 4, 5, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("b", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("r", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("c", 16, 5, SharpFlat.NONE, uMusicChord.MINOR);
		track2.addNextNote("c", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track2.addNextNote("c", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track2.addNextNote("f", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track2.addNextNote("g", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track2.addNextNote("c", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track2.addNextNote("f", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track2.addNextNote("g", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track2.addNextNote("c", 8, 3, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("g", 1, 3, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("e", 2, 3, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("f", 8, 3, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("a", 4, 7, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("b", 8, 7, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("r", 8, 7, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("c", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("g", 1, 5, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("e", 2, 5, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("f", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("a", 4, 5, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("b", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("r", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("c", 16, 5, SharpFlat.DOUBLE_FLAT, uMusicChord.MINOR);
		track2.addNextNote("c", 8, 5, SharpFlat.DOUBLE_FLAT, uMusicChord.MAJOR);
		track2.addNextNote("c", 8, 5, SharpFlat.DOUBLE_FLAT, uMusicChord.MAJOR);
		track2.addNextNote("f", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track2.addNextNote("g", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track2.addNextNote("c", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track2.addNextNote("f", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track2.addNextNote("g", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track2.addNextNote("c", 8, 3, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("g", 1, 3, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("e", 2, 3, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("f", 8, 3, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("a", 4, 7, SharpFlat.NONE, uMusicChord.NONE);
		track2.addNextNote("b", 8, 7, SharpFlat.NONE, uMusicChord.NONE);
	}

	public static void makeTrack3 () {
		track3 = new uMusicTrack (TrackNumber.TRACK3);

		track3.setInstrument("Violin");
		track3.setTempo("Allegro");
		track3.setTimeSignature(4, 4);
		track3.setVolume(120);

		track3.addNextNote("c", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("g", 1, 5, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("e", 2, 5, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("f", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("a", 4, 5, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("b", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("r", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("c", 16, 5, SharpFlat.NONE, uMusicChord.MINOR);
		track3.addNextNote("c", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track3.addNextNote("c", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track3.addNextNote("f", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track3.addNextNote("g", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track3.addNextNote("c", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track3.addNextNote("f", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track3.addNextNote("g", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track3.addNextNote("c", 8, 3, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("g", 1, 3, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("e", 2, 3, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("f", 8, 3, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("a", 4, 7, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("b", 8, 7, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("r", 8, 7, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("c", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("g", 1, 5, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("e", 2, 5, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("f", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("a", 4, 5, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("b", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("r", 8, 5, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("c", 16, 5, SharpFlat.DOUBLE_FLAT, uMusicChord.MINOR);
		track3.addNextNote("c", 8, 5, SharpFlat.DOUBLE_FLAT, uMusicChord.MAJOR);
		track3.addNextNote("c", 8, 5, SharpFlat.DOUBLE_FLAT, uMusicChord.MAJOR);
		track3.addNextNote("f", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track3.addNextNote("g", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track3.addNextNote("c", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track3.addNextNote("f", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track3.addNextNote("g", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR);
		track3.addNextNote("c", 8, 3, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("g", 1, 3, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("e", 2, 3, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("f", 8, 3, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("a", 4, 7, SharpFlat.NONE, uMusicChord.NONE);
		track3.addNextNote("b", 8, 7, SharpFlat.NONE, uMusicChord.NONE);
	}

//	public static void makeTrackFromDisc (uMusicTrack track, uMusicPatternParser parse) {
			
//	}
	
	public static void testManagedPlayer(Sequence seq, Pattern p) throws InvalidMidiDataException, FileNotFoundException, IOException {
		Scanner input = new Scanner(System.in);
		String line;
		boolean runloop = true;
		ManagedPlayer mp = new ManagedPlayer();

		while (runloop) {
			System.out.println("Please enter a command: <start:pause:resume:finish:save:open_midi:open_jfugue> ");
			line = input.nextLine();
			switch (line) {
				case "start":
					try {
						mp.start(seq);
					} catch (MidiUnavailableException ex) {
						Logger.getLogger(UMusic.class.getName()).log(Level.SEVERE, null, ex);
					}
					break;
				case "pause":
					mp.pause();
					break;
				case "resume":
					mp.resume();
					break;
				case "finish":
					mp.finish();
					runloop = false;
					break;
				case "save":
					saveMidi(seq, p);
					break;
				case "open_midi":
					openMidi();
					break;
				case "open_jfugue":
					openJfugue();
					break;
			}
		}
	}

	public static void saveMidi (Sequence s, Pattern p) {
		try {
                	MidiFileManager.save(s, new File("pattern.mid"));
                } catch (IOException e) {
                        e.printStackTrace();
                }
		try {
			p.save(new File("pattern.jfugue"));
		} catch (IOException ex) {
			Logger.getLogger(UMusic.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void openMidi () {
		Pattern pattern1 = null;
		try {
			pattern1 = MidiFileManager.loadPatternFromMidi(new File("pattern.mid"));
		} catch (IOException ex) {
			Logger.getLogger(UMusic.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidMidiDataException ex) {
			Logger.getLogger(UMusic.class.getName()).log(Level.SEVERE, null, ex);
		}
                System.out.println("from the midi file");
                System.out.println(pattern1);

		uMusicPatternParser parser = new uMusicPatternParser(pattern1);
		System.out.println("number tracks is: " + parser.getNumTracks());

		uMusicTrack track = parser.parseTrack(uMusicTrack.TrackNumber.TRACK1);
	}

	
	public static void openJfugue () throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader("pattern.jfugue"));
                Pattern pattern = new Pattern();
                String line = null;
                while ((line = reader.readLine()) != null) {
                        pattern.add(line);
                }
                reader.close();

	 	
		
                System.out.println("from the jfugue file");
                System.out.println(pattern);

		uMusicPatternParser parser = new uMusicPatternParser(pattern);
		System.out.println("number tracks is: " + parser.getNumTracks());

		uMusicTrack track = parser.parseTrack(uMusicTrack.TrackNumber.TRACK1);
	}
	
	public static void main(String[] args) 
		throws InvalidMidiDataException, MidiUnavailableException, FileNotFoundException, IOException {

		makeTrack1();
		makeTrack2();
		makeTrack3();
		
		System.out.println (track1.buildTrackString());
		System.out.println (track2.buildTrackString());
		System.out.println (track3.buildTrackString());

		Sequence seq = new Player().getSequence(track1.buildTrackString(), 
			track2.buildTrackString(), track3.buildTrackString());
		Pattern p = new Pattern(track1.buildTrackString(), 
			track2.buildTrackString(), track3.buildTrackString());
		testManagedPlayer(seq, p);
		
	}
	

}