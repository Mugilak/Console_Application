package view;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.ManageController;
import model.Location;
import model.Screen;
import model.Theatre;

public class AdminManage {
	private Scanner input = new Scanner(System.in);
	private ManageController manage;
	private Location locations;
	private Theatre theatre;
	private Screen screen;
	private String location, locationId, theatreId, theatreName;
	private String screenId, screenName, movieName, noOfSeats, showTime;
	private int choice, loopTime;

	public AdminManage() {
		manage = new ManageController();
	}

	public void init() {
		createLocation();
		createTheatre();
		createScreen();
		addShowTime();
		doProcess();
	}

	private void addShowTime() {
		String choose;
		System.out.println("Add Show Time and movie in a Screen");
		try {
			System.out.println("Enter Screen ID where you want to add Show time (e.g.  100011) : ");
			screenId = input.nextLine();
			screen = manage.checkScreen(Integer.valueOf(screenId));
			if (screen != null) {
				System.out.println("Enter no.of ShowTimes you want to add");
				choice = input.nextInt();
				input.nextLine();
				loopTime = 1;
				loop: while (loopTime <= choice) {
					System.out.println(
							"\nChoose show time\n1. Morning Show\\n2. Noon Show\\n3. Evening Show\\n4. Night Show\\n5. Midnight Show");
					showTime = input.nextLine();
					if (manage.isValid("^[1-5]$", showTime)) {
						if (manage.isUsedShows(showTime) == false) {
							System.out.println("Enter Movie Name to add");
							movieName = input.nextLine();
							manage.addShows(screenId, showTime, movieName, screen);
						} else {
							System.out.println(
									"Showtime you chose already added.... \nif you want to update movie choose it in operations... \nchoose 1-- to Update Movie\n       2-- to Continue");
							choose = input.nextLine();
							choose = check("^[1-2]$", choose, "choice", "choose 1 or 2");
							switch (choose) {
							case "1":
								updateMovie();
								break;
							case "2":
								continue loop;
							}
						}
					} else {
						System.out.println("Choose rigth option for showtime");
						continue loop;
					}
					loopTime++;
				}
			} else {
				System.out.println("No Screen with this Screen ID ..... Continue again");
				addShowTime();
			}
			if (loopTime == choice + 1) {
				System.out.println(choice + " show times added");
			}

		} catch (NullPointerException e) {
			System.out.println("Procees cannot able to go further\n");
		} catch (NumberFormatException e) {
			System.out.println("You have given Invalid input");
		} catch (InputMismatchException e) {
			System.out.println("Invalid input");
		} catch (SQLException e) {
			System.out.println("Cannot able to add Shows ....");
		}
	}

	private void createScreen() {
		System.out.println("Add Screen");
		try {
			System.out.println("Enter Theatre ID where you want to add Screen  (e.g.  10001)");
			theatreId = input.nextLine();
			if ((manage.isValid("^[1-9][0-9][0-9][0-9][1-9]$", theatreId))) {
				theatre = manage.checkTheatre(Integer.valueOf(theatreId));
				if (theatre != null) {
					System.out.println("Enter no.of Screens you want to add");
					choice = input.nextInt();
					input.nextLine();
					int i = 1;
					while (i <= choice) {
						System.out.println("Enter Screen ID (e.g.  100011)  :");
						screenId = input.nextLine();
						if ((manage.isValid("^[1-9][0-9][0-9][0-9][0-9][1-9]$", screenId))) {
							if (manage.checkScreen(Integer.valueOf(screenId)) == null) {
								System.out.println("Enter Screen Block Name : ");
								screenName = input.nextLine();
								screenName = check("^[a-zA-Z]+$", screenName, "Screen Block Name",
										"(No numbers included)");

								if (manage.checkScreen(screenName) == null) {
									System.out.println("Enter No.of Seats in Screen Block : ");
									noOfSeats = input.nextLine();
									noOfSeats = check("^[1-9][0-9]$", noOfSeats, "input",
											"(No Alphabet included ..  e.g. <100)");

									manage.addScreen(theatre, theatreId, screenId, screenName, noOfSeats);
								} else {
									System.out.println("Screen Name already exists ..... Continue again");
									continue;
								}
							} else {
								System.out.println("Screen Id already exists ..... Continue again");
								continue;
							}
						} else {
							System.out.println("invalid Screen ID ..... Continue again");
							continue;
						}
						i++;
					}
					if (i == choice + 1)
						System.out.println("Totally " + choice + " Screens added\n");
				} else {
					System.out.println("No Theatre with this Theatre ID ..... Continue again");
					createScreen();
				}
			} else {
				System.out.println("Invalid Theatre Id ..... Continue again (e.g.  10001)");
				createScreen();
			}
		} catch (NullPointerException e) {
			System.out.println("Procees cannot able to go further\n");
		} catch (NumberFormatException e) {
			System.out.println("You have given Invalid input");
		} catch (InputMismatchException e) {
			System.out.println("Invalid input");
		} catch (SQLException e) {
			System.out.println("Cannot able to add Screen....");
		}
	}

	private void createTheatre() {
		System.out.println("\nAdd Theatre Here");
		try {
			System.out.println("Enter Location ID where you want to add Theatre (e.g.  100)");
			locationId = input.nextLine();
			locations = manage.checkLocation(Integer.valueOf(locationId));
			if (manage.isValid("^[1-9][0-9][0-9]$", locationId)) {
				if (locations != null) {
					System.out.println("Enter no.of Theatres you want to add");
					choice = input.nextInt();
					int i = 1;
					while (i <= choice) {
						input.nextLine();
						System.out.println("Enter theatre ID (e.g.  10001)  :");
						theatreId = input.nextLine();
						if ((manage.isValid("^[1-9][0-9][0-9][0-9][1-9]$", theatreId))) {

							if (manage.checkTheatre(Integer.valueOf(theatreId)) == null) {
								System.out.println("Enter Theatre Name");
								theatreName = input.next();
								theatreName = check("^[a-zA-Z]+$", theatreName, "Theatre Name",
										"(No numbers included)");
								manage.addTheatre(theatreId, theatreName, locationId, locations);
							} else {
								System.out.println("Theatre Id already exists ..... Continue again");
								continue;
							}
						} else {
							System.out.println("Invalid Theatre Id ..... Continue again (e.g.  10001)");
							continue;
						}
						i++;
					}
					if (i == choice + 1)
						System.out.println("Totally " + choice + " THEATRES added\n");
				} else {
					System.out.println("No Location with this Location ID ..... Continue again");
					createTheatre();
				}
			} else {
				System.out.println("Invalid Location ID ..... continue again");
				createTheatre();
			}
		} catch (NumberFormatException e) {
			System.out.println("You have given Invalid input");
		} catch (InputMismatchException e) {
			System.out.println("Invalid input");
		} catch (NullPointerException e) {
			System.out.println("Procees cannot able to go further\n");
		} catch (SQLException e) {
			System.out.println("Cannot able to add Theatre....");
		}
	}

	private void createLocation() {
		System.out.println("Add LOCATION");
		int i = 1;
		try {
			System.out.println("Enter no.of Location you want to add");
			choice = input.nextInt();
			input.nextLine();
			loop: while (i <= choice) {
				System.out.println("Enter Location ID (e.g. 100) : ");
				locationId = input.nextLine();
				if (manage.isValid("^[1-9][0-9][0-9]$", locationId)) {
					if ((manage.checkLocation(Integer.valueOf(locationId))) == null) {
						System.out.println("Enter Location Name (No numbers Included) : ");
						location = input.nextLine();
						location = check("^[a-zA-Z]+$", location, "Location Name", "(No numbers Included)");
						if ((manage.checkLocation(location)) == null) {
							manage.addLocation(locationId, location);
						} else {
							System.out.println("Location already exists ... Continue again\n");
							continue loop;
						}
					} else {
						System.out.println("LocationId already exists ! give correct new Location Id again\n");
						continue loop;
					}
				} else {
					System.out.println("Invalid Location Id ! give Valid Location Id again (e.g. 100) ");
					continue loop;
				}
				i++;
			}
		} catch (NumberFormatException e) {
			System.out.println("You have given Invalid input");
		} catch (SQLException e) {
			System.out.println("Cannot able to add Location....");
		} catch (NullPointerException e) {
			System.out.println("Procees cannot able to go further\n");
		} finally {
			if (i == choice + 1)
				System.out.println(choice + " Locations added\n");
		}
	}

	private String check(String regex, String word, String print, String example) {
		if ((manage.isValid(regex, word)) == false) {
			while ((manage.isValid(regex, word)) == false) {
				System.out.println("Invalid " + print + " !  ----  Enter " + print + " again " + example);
				word = input.nextLine();
			}
		}
		return word;
	}

	public void doProcess() {
		String choice = "2";
		try {
			do {
				System.out.println("""
						\n1 . Add LOCATION
						2 . Add THEATRE
						3 . Add SCREEN
						4 . Add SHOWS
						5 . update MOVIE
						6 . Log out
						press corresponding number to do operation----
						""");
				try {
					String choose = input.nextLine();
					switch (choose) {
					case "1":
						createLocation();
						break;
					case "2":
						createTheatre();
						break;
					case "3":
						createScreen();
						break;
					case "4":
						addShowTime();
						break;
					case "5":
						updateMovie();
						break;
					case "6":
						choice = "1";
						break;
					default:
						System.out.println("Enter valid number to  do operations");
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid input");
				}
			} while (choice != "1");
		} catch (NumberFormatException e) {
			System.out.println("Invalid Number");
		} catch (InputMismatchException e) {
			System.out.println("Invalid input");
		} finally {
			System.out.println("\n ---Thank You---");
		}
	}

	private void updateMovie() {
		String choose;
		System.out.println("Enter Screen ID to update MOVIE");
		screenId = input.nextLine();
		screen = manage.checkScreen(Integer.valueOf(screenId));
		try {
			if (screen != null) {
				System.out.println("Enter no.of showTime movie you want to update");
				choice = input.nextInt();
				input.nextLine();
				loopTime = 1;
				while (loopTime <= choice) {
					System.out.println(
							"\nChoose show time\n1. Morning Show\n2. Noon Show\n3. Evening Show\n4. Night Show\n5. Midnight Show");
					showTime = input.nextLine();
					if (manage.isValid("^[1-5]$", showTime)) {
						if (manage.isUsedShows(showTime) == true) {
							System.out.println("Enter Movie Name to add");
							movieName = input.nextLine();

							if ((manage.updateMovies(screenId, showTime, movieName, screen)) == false) {
								System.out.println(" showtime is not added to update ... ");
								continue;
							}
						} else {
							System.out.println(
									"Showtime you chose is not added .... \nIf you want to add Show Time ... \nchoose 1-- to add Show Time Movie\n       2-- to Continue");
							choose = input.nextLine();
							choose = check("^[1-2]$", choose, "choice", "choose 1 or 2");
							switch (choose) {
							case "1":
								addShowTime();
								break;
							case "2":
								continue;
							}
						}
					} else {
						System.out.println("Choose right option for showtime");
						continue;
					}
					loopTime++;
				}
			} else {
				System.out.println("Invalid Screen Id.... Continue again ");
				updateMovie();
			}
		} catch (SQLException e) {
			System.out.println("Cannot able to update movie ....");
		}
	}

}
