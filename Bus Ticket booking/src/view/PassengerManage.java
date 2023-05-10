package view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import controller.PassengerController;
import model.Bookings;
import model.Bus;

public class PassengerManage extends BusTicket {
	private String boarding, dropping;
	private Scanner input = new Scanner(System.in);
	private PassengerController control;
	private String busId, seats, paid;

	public PassengerManage() {
		control = new PassengerController();
	}

	public void start(String userName) {
		String choice = "";
		try {
			while (true) {
				showInstructions();
				choice = input.nextLine();
				System.out.println();
				switch (choice) {
				case "1":
					bookTicket(userName);
					break;
				case "2":
					cancelTicket(userName);
					break;
				case "3":
					bookingDetails(userName);
					break;
				case "4":
					System.out.println("You are Logged out! \n    Thank You");
					return;
				}
			}
		} catch (SQLException e) {
			System.out.println("cannot proceed .. exited");
			e.printStackTrace();
		}
	}

	private void bookingDetails(String userName) {
		Stack<Bookings> booking = control.getBookingList(userName);
		if (booking.size() > 0) {
			System.out.println("Booking details");
			showDetails(booking);
		} else {
			System.out.println("No bookings yet");
		}
	}

	private void showDetails(Stack<Bookings> booking) {
		System.out.println("|Book Id    |Bus ID   | Seats booked | Boarding  | Dropping |    Amount| is Canceled");
		for (Bookings bookings : booking) {
			System.out.printf("|%10d|", bookings.getBookId());
			System.out.printf("%10d|", bookings.getBusId());
			System.out.printf("%15d|", bookings.getSeatsBooked());
			System.out.printf("%11s|", bookings.getBoarding());
			System.out.printf("%11s|", bookings.getDropping());
			System.out.printf("%10d|", bookings.getPaid());
			System.out.print("   " + bookings.isCanceled());
			System.out.println();
		}
	}

	private void cancelTicket(String userName) throws SQLException {
		System.out.println("Enter booking Id : ");
		String id = input.nextLine();
		id = super.check("^[0-9]$", id, "Booking Id", "(should be in numbers)");
		Bookings booking = control.isIdAvailable(Integer.valueOf(id));
		if (booking != null) {
			Bus bus = control.isBusExists(booking.getBusId());
			control.cancelTicket(booking, bus);
			System.out.println("Cancelled successfully");
		} else {
			System.out.println("Invalid booking id !");
		}
	}

	private void bookTicket(String userName) {
		chooseLocations(userName);
	}

	private void showInstructions() {
		System.out.println("""
				---------------------------

				1 . Book Tickets
				2 . Cancel Tickets
				3 . Print booking details
				4 . Log out

				Enter Choice accordingly :
				""");
	}

	private void chooseLocations(String userName) {
		System.out.println("Choose the boarding point and dropping point :");
		System.out.println("""
				----- Choose a corresponding Locations-----
				 A
				 B
				 C
				 D
				 E
				 F
				""");
		System.out.println("Enter Boarding Point :");
		try {
			boarding = input.nextLine();
			boarding = super.check("^[A-F]{1}$", boarding, "number", "ex : A TO F");
			System.out.println("Enter Dropping Point :");
			dropping = input.nextLine();
			dropping = super.check("^[A-F]{1}$", dropping, "number", "ex : A TO F");
			if (!boarding.equals(dropping)) {
				List<Bus> busList = control.getBuses(boarding, dropping);
				chooseBus(boarding, dropping, busList, userName);
			} else {
				System.out
						.println("---You have wrongly chose same place for both Boarding and Dropping --- Try Again!");
				chooseLocations(userName);
			}
		} catch (NumberFormatException e) {
			System.out.println("wrong input format !");
		} catch (SQLException e) {
			System.out.println("Cannot process ... exited");
			e.printStackTrace();
		} finally {
			System.out.println("Thankyou !");
		}
	}

	private void chooseBus(String boarding, String dropping, List<Bus> busList, String userName)
			throws NumberFormatException, SQLException {
		int count = viewBuses(boarding, dropping, busList);
		if (count != 0) {
			System.out.println("Enter busId");
			busId = input.nextLine();
			busId = super.check("^[0-9]$", busId, "busId", "(should be in numbers)");
			Bus eachBus = control.isBusExists(Integer.valueOf(busId));
			if (eachBus != null) {
				System.out.println("Enter no. of Seats :");
				seats = input.nextLine();
				seats = super.check("^[0-9]$", seats, "number", "ex : 1 to 36");
				int amount = 1000 * Integer.valueOf(seats);
				System.out.println("each person Rs.1000  \n       enter Rs." + amount + " to pay :");
				paid = input.nextLine();
				if (Integer.valueOf(paid) == amount) {
					int id = control.bookTicket(userName, boarding, dropping, Integer.valueOf(seats),
							Integer.valueOf(paid), Integer.valueOf(busId), eachBus);
					if (id == -1) {
						System.out.println("seats unavailable");
					} else
						System.out.println("\nSuccessfully booked !\n Booking id : " + id);
				} else {
					System.out.println("Invalid amount to proceed !");
					chooseBus(boarding, dropping, busList, userName);
				}
			}
		} else
			System.out.println("No buses available for the location ! Exited");
	}

	private int viewBuses(String boarding, String dropping, List<Bus> busList) {
		if (busList.size() > 0) {
			System.out.println("Bus ID     | Total seats | Available Seats  | Used Seats");
			for (Bus bus : busList) {
				System.out.printf("|%10d|", bus.getBusId());
				System.out.printf("%11d|", bus.getSeats());
				System.out.printf("%15d|", bus.getAvailableSeats());
				System.out.printf("%15d|", bus.getUnavailableSeats());
				System.out.println();
			}
		}
		return busList.size();
	}
}
