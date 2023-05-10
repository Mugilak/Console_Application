package model;

public class UserBookings {
	private String userName;
	private int screenId;
	private int noOfSeats;
	private int paid;
	private String showTime;
	private java.sql.Timestamp bookedTime;

	public UserBookings(String userName, int screenId, int noOfSeats, String showTime, int paid) {
		this.noOfSeats = noOfSeats;
		this.paid = paid;
		this.screenId = screenId;
		this.userName = userName;
		this.showTime = showTime;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}

	public int getScreenId() {
		return screenId;
	}

	public void setSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public int getSeats() {
		return noOfSeats;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}

	public int getPaid() {
		return paid;
	}

	public void setBookTime(java.sql.Timestamp bookedTime) {
		this.bookedTime = bookedTime;
	}

	private java.sql.Timestamp getBookTime() {
		return bookedTime;
	}
}
