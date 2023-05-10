package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Theatre {
	private int theatreId;
	private String theatreName;
//	private int screens;
	private int locationId;
	private Stack<Screen> screen;

	public Theatre(int theatreId, String theatreName, int locationId) {
		this.locationId = locationId;
		this.theatreId = theatreId;
		this.theatreName = theatreName;
		screen = new Stack<Screen>();
	}

	public void setScreen(Screen screen) {
		this.screen.push(screen);
	}

	public List<Screen> getScreenList() {
		List<Screen> list = new ArrayList<Screen>();
		Iterator<Screen> itr = screen.iterator();
		while (itr.hasNext()) {
			list.add((Screen) itr.next());
		}
		return list;
	}

	public int getScreens() {
		return screen.size();
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}

	public int getTheatreId() {
		return theatreId;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getLocationId() {
		return locationId;
	}
}
