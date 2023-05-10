package com.cab.controller;

import java.util.List;
import java.util.PriorityQueue;

import com.cab.model.Bookings;
import com.cab.model.Cab;
import com.cab.repository.CabDB;
import com.cab.view.CabBookings;

public class BookingController {
	private CabDB cabDB = CabDB.getInstance();
	private CabBookings book;
	private int bookingId;

	public BookingController(CabBookings book) {
		this.book = book;
	}

	public int bookTaxi(int customerId, char from, char to, int pickupTime, int amount) {
		int taxiNo = -1;
		Cab cab = getFreeTaxi(from - 'A', pickupTime);
		if (cab == null) {
			book.alert("sorry , No Cabs available at this time!");
		} else {
			taxiNo = cab.getCabNo();
			int freeTime = getFreeTime(pickupTime, from, to);
			Bookings booking = new Bookings(++bookingId, customerId, from, to, pickupTime, freeTime, amount);
			cab.setBookings(booking);
			cab.addEarnings(amount);
			cab.setFreeTime(freeTime);
			cab.setPresentAt(to);
			cabDB.setCabsInPoints(to - 'A', cab);
			book.alert("Taxi can be allocated");
		}
		return taxiNo;
	}

	private int getFreeTime(int pickupTime, char from, char to) {
		int freeTime = pickupTime + Math.abs((from - 'A') - (to - 'A'));
		return freeTime;
	}

	private Cab getFreeTaxi(int from, int pickupTime) {
		Cab cab = null;
		List<Cab> cabs = cabDB.getFreeCab(from);
		if (cabs != null) {
			cab = getCab(cabs, pickupTime);
			if (cab != null) {
				return cab;
			}
		}
		int left = from - 1, right = from + 1;
		while (left >= 0 || right < 6) {
			if (left >= 0) {
				cabs = cabDB.getFreeCab(left);
				if (cabs != null) {
					cab = getCab(cabs, pickupTime);
					if (cab != null) {
						return cab;
					}
				}
				left--;
			}
			if (right < 6) {
				cabs = cabDB.getFreeCab(right);
				if (cabs != null) {
					cab = getCab(cabs, pickupTime);
					if (cab != null) {
						return cab;
					}
				}
				right++;
			}
		}
		return cab;
	}

	private Cab getCab(List<Cab> cabs, int pickupTime) {
		for (Cab cab : cabs) {
			if (cab.getFreeTime() <= pickupTime) {
				return cab;
			}
		}
		return null;
	}

	public int calculateFair(char from, char to) {
		int amount = 0;
		int km = 15 * Math.abs((from - 'A') - (to - 'A'));
		int secondHalf = km - 5;
		amount = 100 + (secondHalf * 10);
		return amount;
	}
}
