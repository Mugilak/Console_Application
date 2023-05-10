package com.railway.model;

public class Passenger {
	private static int id = 1;
	private int passengerId, ticketNo, seatNo, age;
	private String name, berth;
	private long phoneNo;
	private boolean isChild;
	private String type;

	public enum Gender {
		MEN(1), WOMEN(2), TRANSGENDER(3);

		private final int number;

		Gender(int number) {
			this.number = number;
		}

		public static Gender valueOf(int number) {
			for (Gender gender : values()) {
				if (gender.number == number) {
					return gender;
				}
			}
			return null;
		}
	}

	private Gender gender;

	public Passenger(String name, int age) {
		this.passengerId = id++;
		this.name = name;
		this.age = age;
	}

	public int getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public int getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(int ticketNo) {
		this.ticketNo = ticketNo;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public boolean isChild() {
		return isChild;
	}

	public void setChild(boolean isChild) {
		this.isChild = isChild;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender optional) {
		this.gender = optional;
	}

	public String getBerth() {
		return berth;
	}

	public void setBerth(String berth) {
		this.berth = berth;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}