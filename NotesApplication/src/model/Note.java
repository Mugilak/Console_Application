package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Note {

	public static List<Note> notes = new ArrayList<>();
	private String basePath = "Notes";
	private String baseFolder = "\\Notes";
	private String fileName = "";
	private final String fileType = ".txt";
	private String filePath;

	public Note(String fileName) {
		this.fileName = fileName;
	}

	public Note(String baseFolder, String fileName) {
		this.fileName = fileName;
		this.baseFolder = baseFolder;
	}

	public void setAbsolutePath() {
		File file = new File(basePath);
		this.basePath = file.getAbsolutePath();
	}

	public void setFilePath() {
		setAbsolutePath();
		this.filePath = this.basePath + "\\" + this.fileName + this.fileType;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFolderBasePath() {
		setAbsolutePath();
		this.filePath = basePath;
	}

	public String getFolderBasePath() {
		return this.filePath;
	}

	public void setFileBasePath() {
		setAbsolutePath();
		this.filePath = this.basePath +this.baseFolder;
	}

	public String getFileBasePath() {
		return this.filePath;
	}

}
