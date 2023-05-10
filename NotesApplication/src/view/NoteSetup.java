package view;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import controller.Operations;
import model.Note;

public class NoteSetup {
	private Note note;
	private Operations operate;
	private Scanner sc = new Scanner(System.in);
	public String folderName, fileName, write;

	NoteSetup() {
		operate = new Operations();
	}

	public void chooseOperation() throws IOException {
		boolean check;
		String loop = "1";
		do {
			System.out.println("""
					1. Create New Note
					2. Delete Note
					3. View Note
					4. edit Note
					5. View Note info
					""");
			System.out.println("Press Corressponding Number to Perform Operation: ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println("You chose Create New Note ! \n");
				System.out.println("Enter New note Name : ");
				String newNote = sc.next();
				note = new Note(newNote);
				note.setFilePath();
				check = operate.create(note.getFilePath());
				if (check == true) {
					System.out.println("\nEnter Text you want to write in the  : ");
					write = sc.next();
					write = write + sc.nextLine();
					operate.doWrite(note.getFilePath(), write);
				} else {
					System.out.println("File Already Exists !");
				}

				break;

			case 2:
				System.out.println("You chose DELETE a Note ! \n");
				System.out.println("""
						Choose
						A --- to delete a Note from specified Folder
						B --- to delete a folder
						""");

				char deleteChoice = sc.next().charAt(0);
				if (deleteChoice == 'a' || deleteChoice == 'A') {
					getInput();
					getPath();
					operate.deleteFile(note.getFileBasePath(), fileName);
				} else if (deleteChoice == 'b' || deleteChoice == 'B') {
					System.out.println("Enter the Folder Name which you want to delete : ");
					sc.nextLine();
					folderName = sc.nextLine();
//				Operations.deleteFolder(folderName);
				}

				break;

			case 3:
				System.out.println("You chose VIEW a Note ! \n");
				getInput();
				getPath();
				if (operate.isFileExist(note.getFileBasePath(), fileName))
					operate.doRead(note.getFilePath(), fileName);
				else
					System.out.println("Note not found");

				break;

			case 4:
				System.out.println("You chose EDIT(add) a Note ! \n");
				getInput();
				getPath();
				if (operate.isFileExist(note.getFileBasePath(), fileName)) {
					System.out.println("\nEnter Text you want to write in the  : ");
					write = sc.next();
					write = write + sc.nextLine();
					operate.doWrite(note.getFilePath(), write);
				} else
					System.out.println("Note not found");

				break;

			case 5:
				System.out.println("You chose view info of a Note ! \n");
				getInput();
				getPath();
				if (operate.isFileExist(note.getFileBasePath(), fileName)) {
					operate.viewInfo(note.getFilePath());
				} else
					System.out.println("Note not found");
				break;

			default:
				System.out.println("Invalid Choice ... Continue again");
				chooseOperation();
			}
			System.out.println("-->Enter any alpha-numeric to continue\n--> press 0 to exit");
			loop = sc.next();
		} while (!(loop.equals("0")));
		System.out.println("----- Thank You for Using NOTES ! -----");
	}

	public void getInput() {
		System.out.println("Enter the Note Name : ");
		fileName = sc.next();
		fileName = fileName + sc.nextLine();

		System.out.println("Enter the folder Name where " + fileName + " note exist (default folder name - Notes): ");
		folderName = sc.nextLine();

	}

	public void getPath() {
		note = new Note(folderName, fileName);
		note.setFilePath();
		note.setFileBasePath();
	}
}

//6. Search Notes(note found / not found)
//7. View All Notes
//8. Create new Folder and a note in it