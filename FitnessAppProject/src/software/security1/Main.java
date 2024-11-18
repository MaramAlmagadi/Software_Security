package software.security1;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		User currentUser = null;

		while (true) {
			System.out.println("Welcome! Please choose an option:");
			System.out.println("1. Sign Up");
			System.out.println("2. Login");
			System.out.println("3. Exit");

			int choice;
			try {
				choice = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a number.");
				continue;
			}

			switch (choice) {
			case 1 -> signUpProcess(scanner);
			case 2 -> {
				System.out.println("Enter email:");
				String email = scanner.nextLine();
				System.out.println("Enter password:");
				String password = scanner.nextLine();
				currentUser = User.login(email, password);
				if (currentUser != null) {
					displayMenu(scanner, currentUser);
				} else {
					System.out.println("Login failed. Please try again.");
				}
			}
			case 3 -> {
				System.out.println("Exiting the program. Goodbye!");
				System.exit(0);
			}
			default -> System.out.println("Invalid choice, please try again.");
			}
		}
	}

	private static void displayMenu(Scanner scanner, User user) {
		while (true) {
			System.out.println("Welcome, " + user.getUsername() + "! Please choose an option:");
			System.out.println("1. View Fitness Plans");
			System.out.println("2. Exit");

			int choice;
			try {
				choice = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a number.");
				continue;
			}

			if (choice == 1) {
				FitnessPlanApp.displayFitnessPlans(scanner, user);
			} else if (choice == 2) {
				System.out.println("Goodbye!");
				System.exit(0);
			} else {
				System.out.println("Invalid choice, please try again.");
			}
		}
	}

	private static void signUpProcess(Scanner scanner) {
		System.out.println("Enter username:");
		String username = scanner.nextLine();
		System.out.println("Enter email:");
		String email = scanner.nextLine();
		System.out.println("Enter password:");
		String password = scanner.nextLine();
		System.out.println("Enter your age:");
		int age;
		try {
			age = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid age. Please enter a number.");
			return;
		}
		User.signUp(username, email, password, age);
	}
}
