package model;

public class Bus {
	private int busId;
	private int seats;
	private int availableSeats;
	private int unavailableSeats;
	private String boardingPoint;
	private String droppingPoint;
	private String travelDate;

	public Bus(int busId, int seats) {
		this.busId = busId;
		this.seats = seats;
	}

	public String getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getBoardingPoint() {
		return boardingPoint;
	}

	public void setBoardingPoint(String boardingPoint) {
		this.boardingPoint = boardingPoint;
	}

	public String getDroppingPoint() {
		return droppingPoint;
	}

	public void setDroppingPoint(String droppingPoint) {
		this.droppingPoint = droppingPoint;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public int getUnavailableSeats() {
		return unavailableSeats;
	}

	public void setUnavailableSeats(int unavailableSeats) {
		this.unavailableSeats = unavailableSeats;
	}

}
