package model;

public class ShowTime {
	private String movieName;
	private String showTime;
	private int screenId;
	
	public ShowTime(int screenId,String showTime,String movieName) {
		this.showTime = showTime;
		this.movieName = movieName;
		this.screenId = screenId;
	}

	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	public int getScreenId() {
		return screenId;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public String getShowTime() {
		return showTime;
	}
}
