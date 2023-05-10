package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Screen {
	private int theatreId;
	private int screenId;
	private String screenName;
	private int seats;
	private List<ShowTime> showTime;

	public Screen(int theatreId, int screenId, String screenName, int seats) {
		this.theatreId = theatreId;
		this.showTime = new ArrayList<ShowTime>();
		this.seats = seats;
		this.screenName = screenName;
		this.screenId = screenId;
	}

	public void setShowTime(ShowTime showTime) {
		this.showTime.add(showTime);
	}

	public List<ShowTime> getShowTime() {
		return showTime;
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}

	public int getTheatreId() {
		return theatreId;
	}

	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	public int getScreenId() {
		return screenId;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getSeats() {
		return seats;
	}

}
