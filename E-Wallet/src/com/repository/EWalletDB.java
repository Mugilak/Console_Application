package com.repository;

import java.util.*;

import com.model.Booking;
import com.model.Destinations;
import com.model.Users;

public class EWalletDB {
	private static EWalletDB eWallet;
	private List<Users> usersList;
	private List<Destinations> destinationList;
	private List<Booking> bookingList;

	private EWalletDB() {
		usersList = new ArrayList<>();
		destinationList = new ArrayList<>();
		bookingList = new ArrayList<>();
	}

	public static EWalletDB getInstance() {
		if (eWallet == null) {
			eWallet = new EWalletDB();
		}
		return eWallet;
	}

	public List<Users> getUsersList() {
		return usersList;
	}

	public void addUsers(Users user) {
		this.usersList.add(user);
	}

	public void setUsersList(List<Users> usersList) {
		this.usersList = usersList;
	}

	public List<Destinations> getDestinationList() {
		return destinationList;
	}

	public void setDestinationList(List<Destinations> destinationList) {
		this.destinationList = destinationList;
	}

	public void addDestination(Destinations destination) {
		this.destinationList.add(destination);
	}

	public List<Booking> getBookingList() {
		return bookingList;
	}

	public void setBookingList(List<Booking> bookingList) {
		this.bookingList = bookingList;
	}
	
	public void addBookingList(Booking booking) {
		this.bookingList.add(booking);
	}

}
