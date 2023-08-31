package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Bookings;
import model.Bus;
import model.BusBookingDatabase;
import model.Passenger;

public class BusBooking {
	private static BusBooking database;
	private Statement statement;
	private ResultSet result;
	private BusBookingDatabase busDB = BusBookingDatabase.getInstance();

	public static BusBooking getInstance() {
		if (database == null) {
			database = new BusBooking();
		}
		return database;
	}

	private Statement setConnection() {
		String url = "jdbc:mysql://localhost:3306/busticket";
		String username = "root";
		String pwd = "mugi123";
		try {
			Connection con = DriverManager.getConnection(url, username, pwd);
			statement = con.createStatement();
			return statement;
		} catch (Exception e) {
			System.out.println("Connection Failed");
		}
		return null;
	}

	public void setData(String query) throws SQLException {
		statement = setConnection();
		int i = statement.executeUpdate(query);
		if (i < 0) {
			System.out.println("Sorry for the inconveniencce....Cannot able to register !");
		}
	}

	public void getBusData() {
		statement = setConnection();
		try {
			result = statement.executeQuery("select * from busticket.bus");
			while (result.next()) {
				Bus bus = new Bus(result.getInt(1), result.getInt(2));
				bus.setAvailableSeats(result.getInt(3));
				bus.setUnavailableSeats(result.getInt(4));
				bus.setBoardingPoint(result.getString(5));
				bus.setDroppingPoint(result.getString(6));
				bus.setTravelDate(result.getString(7));
				busDB.setBusList(bus);
			}
		} catch (SQLException e) {
			System.out.println("Cannot able to retrieve your data ! Thankyou");
		}
	}

	public void getPassengerData() {
		statement = setConnection();
		try {
			result = statement.executeQuery("select * from busticket.passenger");
			while (result.next()) {
				Passenger passenger = new Passenger(result.getString(1), result.getString(2), result.getInt(8));
				passenger.setPassengerDetails(result.getString(3), result.getString(4), result.getString(5),
						result.getString(6), result.getInt(7));
				busDB.setPassengerList(passenger);
			}
		} catch (SQLException e) {
			System.out.println("Cannot able to retrieve your data ! Thankyou");
		}
	}

	public void getBookingsData() {
		statement = setConnection();
		boolean choice = false;
		try {
			result = statement.executeQuery("select * from busticket.bookings");
			while (result.next()) {
				choice = false;
				Bookings bookings = new Bookings(result.getString(1), result.getString(2), result.getString(3),
						result.getString(4), result.getInt(5), result.getInt(6), result.getInt(7));
				bookings.setBookId(result.getInt(8));
				if (result.getInt(9) == 1) {
					choice = true;
				}
				bookings.setCanceled(choice);
				busDB.setBookingsList(bookings);
			}
		} catch (SQLException e) {
			System.out.println("Cannot able to retrieve your data ! Thankyou");
		}
	}
}
