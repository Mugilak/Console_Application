package com.view;

import java.util.*;
import java.util.Scanner;

import com.controller.UserController;
import com.model.Booking;
import com.model.Destinations;
import com.model.Users;

public class UserManage {
	private Scanner input = new Scanner(System.in);
	private UserController control;
	private Users users;

	public UserManage() {
		control = new UserController(this);
	}

	public void start(Users users) {
		this.users = users;
		char choice = 'f';
		try {
			do {
				System.out.println();
				System.out.println("""
						a. Update the wallet amount
						b. Show Transaction History
						c. Book Train Ticket
						d. Show the valid ticket
						e. Delete Account
						f. Logout
						""");
				choice = input.next().charAt(0);
				input.nextLine();
				switch (choice) {
				case 'a':
					updateWallet();
					break;
				case 'b':
					showTransaction();
					break;
				case 'c':
					bookTicket();
					break;
				case 'd':
					getTicket();
					break;
				case 'e':
					deleteAccount();
					break;
				case 'f':
					choice = 'f';
					break;
				default:
					System.out.println("Invalid input ");
				}
			} while (choice != 'f');
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input");
		}
	}

	private void getTicket() {

	}

	private void deleteAccount() {

	}

	private void showTicket(Booking book) {
		String ticket = "";
		System.out.println("Ticket\n---------------------------");
		ticket += "UTS No. : " + book.getUtsNo() + "\nPassengers : " + book.getPassengers() + "\nFrom : "
				+ book.getFrom() + "\nTo : " + book.getTo() + "\nStart Time : " + book.getStart() + "\nEnd Time : "
				+ book.getEnd();
		System.out.println(ticket + "\n---------------------------");
	}

	private void showTransaction() {
		Stack<Booking> transactions = users.getTransactions();
		System.out.println(" UTS No.||passengers||            From||                To||  Amount    ");
		while ((transactions.isEmpty()) == false) {
			Booking book = transactions.pop();
			System.out.println(" " + book.getUtsNo() + "||         " + book.getPassengers() + "||  " + book.getFrom()
					+ "||        " + book.getTo() + "||" + book.getAmount());
		}
	}

	private void bookTicket() {
		System.out.println("Enter No. of passengers : ");
		String passengers = input.nextLine();
		System.out.println("Enter from : ");
		String from = input.nextLine();
		System.out.println("Enter to : ");
		String to = input.nextLine();
		Booking book = control.bookTicket(from, to, passengers, users);
		if (book != null) {
			System.out.println("Booked successfully !\n");
			showTicket(book);
		} else
			System.out.println("Invalid entry");
	}

	private void updateWallet() {
		System.out.println("-------\nEnter amount to update in your wallet : ");
		String amount = input.nextLine();
		if (Integer.valueOf(amount) > 0 && Integer.valueOf(amount) <= 10000) {
			control.updateWallet(users, Integer.valueOf(amount));
			System.out.println(amount + " added to your account !");
		} else
			System.out.println("Invallid !! amount should be within 1 to 10000 ");
	}

	public boolean doFillMoney(int amount, int wallet) {
		System.out.println("\nInsufficient Balance ! \nAmount to pay : " + amount + "---but you have : " + wallet);
		System.out.println("Want to update Amount? : (press 1- to Update money  2- no need, discontinue");
		String choice = input.nextLine();
		if (choice.equals("1")) {
			updateWallet(amount + wallet);
			return true;
		} else if (choice.equals("2")) {
			return false;
		} else {
			System.out.println("Invalid Input");
		}
		return false;
	}

	private void updateWallet(int target) {
		System.out.println("-------\nEnter amount to update in your wallet : ");
		int amount = input.nextInt();
		while (amount < target) {
			System.out.println("amount should be greater than target ... Enter again");
			amount = input.nextInt();
		}
		if (amount >= target && amount > 0 && amount <= 10000) {
			control.updateWallet(users, amount);
			System.out.println(amount + " added to your account !");
		} else
			System.out.println("amount should be greater than target ");
	}

	public boolean alert(int fare) {
		System.out.println("You have total fare of Rs. " + fare + "\n(1- pay, 2- Cancle)");
		String choice = input.nextLine();
		if (choice.equals("1")) {
			return true;
		} else if (choice.equals("2")) {
			return false;
		} else {
			System.out.println("Invalid Input");
		}
		return false;
	}

	public void alertMsg(String string) {
		System.out.println(string);
	}

}
