package umusic;

import java.util.Scanner;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class UMusic {

    public static void main(String[] args) throws InvalidMidiDataException, MidiUnavailableException {

		uMusicPlayer ump = new uMusicPlayer();

		ump.run();

		Scanner input = new Scanner(System.in);

		String line;
		boolean runloop = true;

		while (runloop) {
			System.out.println("Please enter a command: <start:pause:resume:finish> ");
			line = input.nextLine();
			switch (line) {
				case "start":
					ump.start();
					break;
				case "pause":
					ump.pause();
					break;
				case "resume":
					ump.resume();
					break;
				case "finish":
					ump.finish();
					runloop = false;
					break;
			}
		}
	}
}