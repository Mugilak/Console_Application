package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Location {
	private int locationId;
	private String location;
	private Stack<Theatre> theatreList;
	
	public Location(int locationId,String location) {
		this.locationId = locationId;
		this.location = location;
		theatreList = new Stack<Theatre>();
	}

	public void setTheatre(Theatre theatre) {
		this.theatreList.push(theatre);
	}

	public List<Theatre> getTheatreList() {
		List<Theatre> list = new ArrayList<Theatre>();
		Iterator<Theatre> itr = theatreList.iterator();
		while (itr.hasNext()) {
			list.add((Theatre) itr.next());
		}
		return list;
	}
	
	public int getTheatres() {
		return theatreList.size();
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}
}
