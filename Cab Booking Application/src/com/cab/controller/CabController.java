package com.cab.controller;

import java.util.*;

import com.cab.model.Cab;
import com.cab.repository.CabDB;

public class CabController {
	private CabDB cabDB = CabDB.getInstance();

	public void createCabs(int cabsCount) {
		for (int i = 0; i < cabsCount; i++) {
			Cab cab = new Cab(i + 1, 'A');
			cabDB.setCabsInPoints(0, cab);
			cabDB.setCab(cab);
		}
	}

	public void addPoints(int pointsCount) {
		cabDB.setPoints(pointsCount);
	}

	public Cab getTaxi(int taxiNo) {
		List<Cab> cabList = cabDB.getCabsList();
		for (Cab cab : cabList) {
			if (cab.getCabNo() == taxiNo) {
				return cab;
			}
		}
		return null;
	}

}
