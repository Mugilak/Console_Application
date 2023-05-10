package com.cab.repository;

import java.util.*;

import com.cab.model.*;

public class CabDB {
	private static CabDB cabDB;
	private List<Cab> cabsList;
	private List<ArrayList<Cab>> points;

	private CabDB() {
		this.cabsList = new ArrayList<Cab>();
		this.points = new ArrayList<ArrayList<Cab>>();
	}

	public static CabDB getInstance() {
		if (cabDB == null) {
			cabDB = new CabDB();
		}
		return cabDB;
	}

	public void setPoints(int pointsCount) {
		for (int i = 0; i < pointsCount; i++) {
			points.add(null);
		}
	}

	public List<Cab> getCabsList() {
		return cabsList;
	}

	public void setCab(Cab cab) {
		this.cabsList.add(cab);
	}

	public ArrayList<Cab> getFreeCab(int index) {
		if (points.get(index) != null) {
			int size = points.get(index).size();
			if (size > 0) {
				ArrayList<Cab> list = points.get(index);
				Collections.sort(list, new Comparator<Cab>() {

					@Override
					public int compare(Cab c1, Cab c2) {
						return Integer.valueOf(c1.getEarnings()).compareTo(c2.getEarnings());
					}

				});
				return list;
			}
		}
		return null;
	}

	public void removeCab(int index, Cab cab) {
		points.get(index).remove(index);
	}

	public int getTaxisSize(int index) {
		return points.get(index).size();
	}

	public void setCabsInPoints(int index, Cab cab) {
		if (points.get(index) == null) {
			ArrayList<Cab> list = new ArrayList<>();
			list.add(cab);
			points.add(index, list);
		} else
			this.points.get(index).add(cab);
	}

}
