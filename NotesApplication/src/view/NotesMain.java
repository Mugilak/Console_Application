package view;

import java.io.IOException;

public class NotesMain {
	private NoteSetup sets;

	NotesMain() {
		sets= new NoteSetup();
	}

	public static void main(String[] args) throws IOException {
		NotesMain main = new NotesMain();
		main.start();
	}

	private void start() throws IOException {
		System.out.println("------------- NOTES APPLICATION -------------\n");
		sets.chooseOperation();
	}

}
