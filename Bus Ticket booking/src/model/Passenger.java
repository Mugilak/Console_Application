package model;

public class Passenger {
	private String userName;
	private String password;
	private String name;
	private String DOB;
	private String address;
	private String emailId;
	private long phoneNo;
	private int age;

	public Passenger(String userName, String password, int age) {
		this.userName = userName;
		this.password = password;
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setPassengerDetails(String name, String DOB, String address, String emailId, long phoneNo) {
		this.name = name;
		this.DOB = DOB;
		this.address = address;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String DOB) {
		this.DOB = DOB;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

}
