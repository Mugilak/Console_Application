package com.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminManage {
	private Scanner input = new Scanner(System.in);

	public void start() {
		char choice = 'f';
		try {
			do {
				System.out.println("""
						a. Provide Train Schedule
						b. Check Revenue
						c. Logout
						""");
				choice = input.next().charAt(0);
				switch (choice) {
				case 'a':
					ProvideTrain();
					break;
				case 'b':
					getRevenue();
					break;
				case 'c':
					choice ='c';
					break;
				default :
					System.out.println("Invalid input");
				}
			} while (choice == 'c');
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input");
		}
	}

	private void getRevenue() {
		
	}

	private void ProvideTrain() {
		// TODO Auto-generated method stub
		
	}

}
