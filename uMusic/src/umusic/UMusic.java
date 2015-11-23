package umusic;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import umusic.uMusicNote.Inversion;
import umusic.uMusicNote.uMusicChord;
import umusic.uMusicNote.SharpFlat;
import umusic.uMusicTrack.TrackNumber;

public class UMusic extends Application {
	static ArrayList <uMusicNote> voice1 = new ArrayList<>();

	static uMusicTrack track1;
	static uMusicTrack track2;
	static uMusicTrack track3;

        
            @Override
        public void start(Stage stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("UMusicFXML.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        }
        
	public static void makeTrack1 (uMusicSongController sc) {
            sc.addTrack(TrackNumber.TRACK0, "Track 1");
            sc.setInstrument(TrackNumber.TRACK0, "Flute");
            sc.setTrackVolume(TrackNumber.TRACK0, 100);

            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("c", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("g", 1, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("e", 2, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("f", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("a", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("b", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("r", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("c", 16, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("c", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("c", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("f", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("g", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("c", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("f", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("g", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("c", 8, 3, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("g", 1, 3, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("e", 2, 3, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("f", 8, 3, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("a", 4, 7, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("b", 8, 7, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("r", 8, 7, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("c", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("g", 1, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("e", 2, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("f", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("a", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("b", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("r", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("c", 16, 5, SharpFlat.DOUBLE_FLAT));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("c", 8, 5, SharpFlat.DOUBLE_FLAT));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("c", 8, 5, SharpFlat.DOUBLE_FLAT));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("f", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("g", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("c", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("f", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("g", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("c", 8, 3, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("g", 1, 3, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("e", 2, 3, SharpFlat.SHARP));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("f", 8, 3, SharpFlat.SHARP));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("a", 4, 7, SharpFlat.SHARP));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("b", 8, 7, SharpFlat.SHARP));

            sc.deleteLastNote(TrackNumber.TRACK0); // delete the last note added
            sc.editNote(TrackNumber.TRACK0, 0, new uMusicNote("b", 8, 7, SharpFlat.SHARP));
	}

	public static void makeTrack2 (uMusicSongController sc) {
            sc.addTrack(TrackNumber.TRACK1, "Piano Track");
            sc.setInstrument(TrackNumber.TRACK1, "Piano");
            sc.setTrackVolume(TrackNumber.TRACK1, 100);

	    sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("c", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("g", 1, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("e", 2, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("f", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("a", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("b", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("r", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("c", 16, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("c", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("c", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("f", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("g", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("c", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("f", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("g", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("c", 8, 3, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("g", 1, 3, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("e", 2, 3, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("f", 8, 3, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("a", 4, 7, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("b", 8, 7, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("r", 8, 7, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("c", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("g", 1, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("e", 2, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("f", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("a", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("b", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("r", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("c", 16, 5, SharpFlat.DOUBLE_FLAT));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("c", 8, 5, SharpFlat.DOUBLE_FLAT));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("c", 8, 5, SharpFlat.DOUBLE_FLAT));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("f", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("g", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("c", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("f", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("g", 8, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("c", 8, 3, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("g", 1, 3, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("e", 2, 3, SharpFlat.SHARP));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("f", 8, 3, SharpFlat.SHARP));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("a", 4, 7, SharpFlat.SHARP));
            sc.addNoteToTrack(TrackNumber.TRACK1, new uMusicNote("b", 8, 7, SharpFlat.SHARP));
	}

	public static void makeTrack3 (uMusicSongController sc) {
            sc.addTrack(TrackNumber.TRACK2, "Violin Track");
            sc.setInstrument(TrackNumber.TRACK2, "Violin");
            sc.setTrackVolume(TrackNumber.TRACK2, 100);

	    sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("c", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR, Inversion.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("c", 4, 5, SharpFlat.NONE, uMusicChord.MINOR, Inversion.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("c", 2, 5, SharpFlat.NONE, uMusicChord.MAJOR7, Inversion.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("c", 8, 5, SharpFlat.NONE, uMusicChord.MINOR7, Inversion.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("c", 4, 5, SharpFlat.NONE, uMusicChord.MAJOR9, Inversion.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("c", 8, 5, SharpFlat.NONE, uMusicChord.MINOR9, Inversion.NONE));

	    sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("b", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR, Inversion.SINGLE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("b", 4, 5, SharpFlat.NONE, uMusicChord.MINOR, Inversion.SINGLE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("b", 2, 5, SharpFlat.NONE, uMusicChord.MAJOR7, Inversion.SINGLE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("b", 8, 5, SharpFlat.NONE, uMusicChord.MINOR7, Inversion.SINGLE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("b", 4, 5, SharpFlat.NONE, uMusicChord.MAJOR9, Inversion.SINGLE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("b", 8, 5, SharpFlat.NONE, uMusicChord.MINOR9, Inversion.SINGLE));

	    sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("g", 8, 5, SharpFlat.NONE, uMusicChord.MAJOR, Inversion.DOUBLE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("g", 4, 5, SharpFlat.NONE, uMusicChord.MINOR, Inversion.DOUBLE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("g", 2, 5, SharpFlat.NONE, uMusicChord.MAJOR7, Inversion.DOUBLE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("g", 8, 5, SharpFlat.NONE, uMusicChord.MINOR7, Inversion.DOUBLE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("g", 4, 5, SharpFlat.NONE, uMusicChord.MAJOR9, Inversion.DOUBLE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("g", 8, 5, SharpFlat.NONE, uMusicChord.MINOR9, Inversion.DOUBLE));
	    
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("r", 1, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("r", 1, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("r", 1, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("r", 1, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("r", 1, 5, SharpFlat.NONE));
	    
	    sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("g", 4, 5, SharpFlat.FLAT, uMusicChord.MAJOR, Inversion.DOUBLE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("g", 4, 5, SharpFlat.FLAT, uMusicChord.MINOR, Inversion.DOUBLE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("g", 4, 5, SharpFlat.FLAT, uMusicChord.MAJOR7, Inversion.DOUBLE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("g", 4, 5, SharpFlat.FLAT, uMusicChord.MINOR7, Inversion.DOUBLE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("g", 4, 5, SharpFlat.FLAT, uMusicChord.MAJOR9, Inversion.DOUBLE));
            sc.addNoteToTrack(TrackNumber.TRACK2, new uMusicNote("g", 4, 5, SharpFlat.FLAT, uMusicChord.MINOR9, Inversion.DOUBLE));
	    
	}

	//Test 1 tests adding 3 tracks to the song controller.  Notes are added to each track.
	//The uMusicSongController is passed to the uMusicPlayerController to be played
	public static void test1(uMusicSongController sc, uMusicPlayerController pc, Scanner input) {
		String line;

		sc.setName("My Song");
		sc.setTempo("Allegro");
		sc.setTimeSignature(4, 4);
		sc.setMasterVolume(125);
		
		makeTrack1(sc);
		makeTrack2(sc);
		makeTrack3(sc);
	
		pc.addSong(sc);

		boolean runloop = true;
		while (runloop) {
			System.out.println("Please enter a command: <start:pause:finish> ");
			line = input.nextLine();
			switch (line) {
				case "start":
					pc.startSong();
					break;
				case "pause":
					pc.pauseSong();
					break;
				case "finish":
					pc.finishSong();
					runloop = false;
					break;
			}
		}
	}

	public static void test2(uMusicPlayerController pc, Scanner input) {
		String line;
		int volume = 100;
		int _volume = 0;

		boolean runloop = true;
		while (runloop) {
			System.out.println("Please enter a note and press <enter>: <a:b:c:d:e:f:g:volume:finish> ");

			line = input.nextLine();
			switch (line) {
				case "a":
				case "b":
				case "c":
				case "d":
				case "e":
				case "f":
				case "g":
					uMusicNote note = new uMusicNote(line, 8, 5, SharpFlat.NONE);
					pc.playLiveNote(note, volume);
					break;
				case "volume":
					System.out.println("Please enter a value between 0 and 125");
					_volume = Integer.parseInt(input.nextLine());
					if (volume < 0 || volume > 125)
						System.out.println("Invalid value");
					else
						volume = _volume;
					break;
				case "finish":
					runloop = false;
					break;
			}
		}
	}

	public static void test3(uMusicSongController sc, uMusicPlayerController pc, Scanner input) {
		String line;
		int volume = 100;
		int _volume = 0;

		sc.setTimeSignature(4, 4);
		sc.setMasterVolume(volume);
		sc.addTrack(TrackNumber.TRACK0, "Track 1");
                sc.setInstrument(TrackNumber.TRACK0, "Flute");
                sc.setTrackVolume(TrackNumber.TRACK0, volume);

		boolean runloop = true;
		while (runloop) {
			System.out.println("Please enter a note and press <enter>: <a:b:c:d:e:f:g:volume:start:pause:finish> ");

			line = input.nextLine();
			switch (line) {
				case "a":
				case "b":
				case "c":
				case "d":
				case "e":
				case "f":
				case "g":
					uMusicNote note = new uMusicNote(line, 8, 5, SharpFlat.NONE);
					uMusicNote copy = new uMusicNote(note);
					pc.playLiveNote(note, volume);
					sc.addNoteToTrack(TrackNumber.TRACK0, copy);
					break;
				case "volume":
					System.out.println("Please enter a value between 0 and 125");
					_volume = Integer.parseInt(input.nextLine());
					if (volume < 0 || volume > 125) {
						System.out.println("Invalid value");
					} else {
						sc.setMasterVolume(volume);
						volume = _volume;
					}
					break;
				case "start":
					pc.addSong(sc);
					pc.startSong();
					break;
				case "pause":
					pc.pauseSong();
					break;
				case "finish":
					pc.finishSong();
					runloop = false;
					break;
			}
		}
	}

	public static void makeTwinkle (uMusicSongController sc) {
            sc.addTrack(TrackNumber.TRACK0, "Track 1");
            sc.setInstrument(TrackNumber.TRACK0, "Piano");
            sc.setTrackVolume(TrackNumber.TRACK0, 100);

            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("c", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("c", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("g", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("g", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("a", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("a", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("g", 2, 5, SharpFlat.NONE));

            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("f", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("f", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("e", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("e", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("d", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("d", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("c", 2, 5, SharpFlat.NONE));

            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("g", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("g", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("f", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("f", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("e", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("e", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("d", 2, 5, SharpFlat.NONE));

            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("g", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("g", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("f", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("f", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("e", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("e", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("d", 2, 5, SharpFlat.NONE));

            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("c", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("c", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("g", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("g", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("a", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("a", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("g", 2, 5, SharpFlat.NONE));

            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("f", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("f", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("e", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("e", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("d", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("d", 4, 5, SharpFlat.NONE));
            sc.addNoteToTrack(TrackNumber.TRACK0, new uMusicNote("c", 2, 5, SharpFlat.NONE));
	}

	//create the song twinkle, twinkle, little star
	public static void test4(uMusicSongController sc, uMusicPlayerController pc, Scanner input) {
		String line;

		sc.setName("Twinkle Twinkle Little Star");
		sc.setTempo("Allegro");
		sc.setTimeSignature(4, 4);
		sc.setMasterVolume(125);
	
		makeTwinkle(sc);	
		pc.addSong(sc);

		boolean runloop = true;
		while (runloop) {
			System.out.println("Please enter a command: <start:pause:finish> ");
			line = input.nextLine();
			switch (line) {
				case "start":
					pc.startSong();
					break;
				case "pause":
					pc.pauseSong();
					break;
				case "finish":
					pc.finishSong();
					runloop = false;
					break;
			}
		}
	}

	public static void test5(uMusicSongController sc, uMusicPlayerController pc, Scanner input) {
            uMusicNote note = new uMusicNote("c", 4, 5, SharpFlat.NONE);

	    System.out.println("jfugue: " + note.toString() + " lilypond: " + note.toLilyPond());
	}
	
	public static void test6(uMusicSongController sc, uMusicPlayerController pc, Scanner input) {
		String line;
		sc.setTempo("Allegro");
		sc.setTimeSignature(4, 4);
		sc.setMasterVolume(125);
	    
            uMusicRhythm r = new uMusicRhythm("beat1");
	    r.setRhythmDuration(8);
	    r.setRhythmLayer(uMusicRhythm.PercussionInstrument.BASS,          "yy...yy...yy..yy");
	    r.setRhythmLayer(uMusicRhythm.PercussionInstrument.CLOSED_HI_HAT, ".y....y....y...y");
	    r.setRhythmLayer(uMusicRhythm.PercussionInstrument.OPEN_HI_HAT,   "..y....y....y..y");
	    r.setRhythmLayer(uMusicRhythm.PercussionInstrument.SNARE,         "...y....y....y.y");
	    r.setRhythmLayer(uMusicRhythm.PercussionInstrument.CYMBAL,        "...yy...yy....yy");

	    System.out.println("jfugue: " + r.toString());

	    uMusicRhythm r2 = new uMusicRhythm("beat2");
	    r2.setRhythmDuration(16);
	    r2.setRhythmLayer(uMusicRhythm.PercussionInstrument.BASS,          "yyy..yyy..yyy.yy");
	    r2.setRhythmLayer(uMusicRhythm.PercussionInstrument.CLOSED_HI_HAT, "................");
	    r2.setRhythmLayer(uMusicRhythm.PercussionInstrument.OPEN_HI_HAT,   "................");
	    r2.setRhythmLayer(uMusicRhythm.PercussionInstrument.SNARE,         "................");
	    r2.setRhythmLayer(uMusicRhythm.PercussionInstrument.CYMBAL,        "...yy...yy....yy");

	    pc.playLiveRhythm(r, 100);
	    pc.playLiveRhythm(r2, 100);
	    pc.playLiveRhythm(r, 100);
	    pc.playLiveRhythm(r2, 100);

	    sc.addPercussionTrack("Percussion Track");
	    sc.setPercussionTrackVolume(100);
	    sc.addRhythmToTrack(new uMusicRhythm(r));
	    sc.addRhythmToTrack(new uMusicRhythm(r2));
	    sc.addRhythmToTrack(new uMusicRhythm(r));
	    sc.addRhythmToTrack(new uMusicRhythm(r2));
	
	    pc.addSong(sc);
	    boolean runloop = true;
		while (runloop) {
			System.out.println("Please enter a command: <start:pause:finish> ");
			line = input.nextLine();
			switch (line) {
				case "start":
					pc.startSong();
					break;
				case "pause":
					pc.pauseSong();
					break;
				case "finish":
					pc.finishSong();
					runloop = false;
					break;
			}
		}
	}
	
	public static void saveSong(uMusicSongController sc, Scanner input){
            String fileName;
            System.out.println("Please enter a file name:");
            fileName = input.nextLine();
            uMusicFileController fc = new uMusicFileController(sc);
            fc.save(fileName);
        }
        
        public static uMusicSongController loadSong(uMusicSongController sc, uMusicPlayerController pc, Scanner input){
            String fileName;
            System.out.println("Please select a song: <1:2:3:4> ");
			fileName = input.nextLine();
			switch (fileName) {
				case "1":
					System.out.println("Selected Song 1");
					break;
				case "2":
					System.out.println("Selected Song 2");
					break;
				case "3":
					System.out.println("Selected Song 3");
					break;
                                case "4":
					System.out.println("Selected Song 4");
					break;
                                default:
                                        System.out.println("Selected Song 1");
                                        fileName = "1";
			}
			
            uMusicFileController fc = new uMusicFileController(sc);
            sc = fc.load(fileName);
            pc.addSong(sc);
            String line;
            boolean runloop = true;
		while (runloop) {
			System.out.println("Please enter a command: <start:pause:finish> ");
			line = input.nextLine();
			switch (line) {
				case "start":
					pc.startSong();
					break;
				case "pause":
					pc.pauseSong();
					break;
				case "finish":
					pc.finishSong();
					runloop = false;
					break;
			}
                }
            return sc;
        }

	public static void main(String[] args) {

		uMusicSongController sc = new uMusicSongController();
		uMusicPlayerController pc = new uMusicPlayerController();
		Scanner input = new Scanner(System.in);
		String line;


		boolean runloop = true;
		while (runloop) {
			System.out.println("Please enter the test number, gui, save, load, 'help' or 'quit' to exit: <1:2:3:4:5:6:gui:save:load:help:quit> ");
			line = input.nextLine();
			switch (line) {
				case "1":
		                    test1(sc, pc, input);
				    break;
				case "2":
				    test2(pc, input);
				    break;
				case "3":
		                    test3(sc, pc, input);
				    break;
				case "4":
		                    test4(sc, pc, input);
				    break;
				case "5":
		                    test5(sc, pc, input);
				    break;
				case "6":
		                    test6(sc, pc, input);
				    break;
                                case "gui":
                                    launch(args);
                                    break;
				case "help":
					System.out.println("Test 1 : Creates 3 tracks, adds notes to the tracks and then allows");
					System.out.println("         the user to control playback (start, pause and finish)");
					System.out.println("Test 2 : Accepts notes (a,b,c,d,e,f,g) from the keyboard to be played live");
					System.out.println("Test 3 : Combined test 1 and test 2.  As notes are played live, the");
					System.out.println("         the notes are stored in a single track of a song.");
					System.out.println("Test 4 : Play Twinkle, Twinkle, Little Star");
					System.out.println("Test 5 : Test the LiliPond Parser code");
					System.out.println("Test 6 : Test the Percussion Track");
					break;
				case "quit":
					runloop = false;
					break;
				case "save":
                                    saveSong(sc, input);
                                    break;
                                case "load":
                                    sc = loadSong(sc, pc, input);
                                    break;
			}
		}
		
		System.out.println("END");
		return;
	}
}
