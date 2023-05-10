package com.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import com.model.Booking;
import com.model.Destinations;
import com.model.Users;
import com.repository.EWalletDB;
import com.view.UserManage;

public class UserController {
	private EWalletDB eWallet = EWalletDB.getInstance();
	private UserManage user;
	private static int utsNo = 3475758;
	private int amount;

	public UserController(UserManage userManage) {
		this.user = userManage;
	}

	public void updateWallet(Users users, int amount) {
		users.addAmount(amount);
	}

	public void getWalletAmount(Users users, int amount) {
		users.addAmount(amount);
	}

	public Destinations isAvailablePlace(String from) {
		List<Destinations> list = eWallet.getDestinationList();
		for (Destinations destination : list) {
			if (destination.getDestination().equalsIgnoreCase(from)) {
				return destination;
			}
		}
		return null;
	}

	public Booking bookTicket(String from, String to, String passengers, Users users) {
		long hour = 3600 * 1000;
		Destinations start = isAvailablePlace(from);
		Destinations end = isAvailablePlace(to);
//		System.out.println(from.equalsIgnoreCase(to));
		if ((from.equalsIgnoreCase(to) == false) && start != null && end != null && (start.getKm() < end.getKm())) {
			if (getMoney(start.getKm(), end.getKm(), users, Integer.valueOf(passengers))) {
				String startTime = new SimpleDateFormat().format(Calendar.getInstance().getTime());
				String endTime = new SimpleDateFormat().format(new Date().getTime() + hour);
				Booking book = new Booking(++utsNo, Integer.valueOf(passengers), from, to, amount, startTime, endTime);
				users.addTransaction(book);
				Booking.setRevenue(amount);
				eWallet.addBookingList(book);
				return book;
			}
			user.alertMsg("km less than 5");
		}
		return null;
	}

	private boolean getMoney(int km, int km2, Users users, int passengers) {
		amount = ((km2 - km) / 10) * 5;
		if (amount >= 5 && amount <= 25) {
			if (users.getAmount() >= (amount * passengers)) {
				if (user.alert(amount * passengers)) {
					return true;
				}
			} else {
				if (user.doFillMoney(amount * passengers, users.getAmount())) {
					return true;
				}
			}
		}
		return false;
	}

}
