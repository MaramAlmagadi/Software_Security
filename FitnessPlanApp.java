package software.security1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FitnessPlanApp {
	private static final List<FitnessPlan> plans = new ArrayList<>();

	static {
		plans.add(new FitnessPlan("Cardio", 150, "beginner", "Weight Loss"));
//plans.add(new FitnessPlan("Cardio", 180, "intermediate", "Weight Loss"));
//plans.add(new FitnessPlan("Strength", 150, "beginner", "Muscle Building"));
		plans.add(new FitnessPlan("Strength", 120, "intermediate", "Muscle Building"));
//plans.add(new FitnessPlan( "Strength", 210, "advanced", "Muscle Building"));
//plans.add(new FitnessPlan("Cardio", 210, "advanced", "Weight Loss"));
		plans.add(new FitnessPlan("Yoga", 120, "beginner", "Stress Relief"));
//plans.add(new FitnessPlan( "Yoga", 180, "intermediate", "Improve Flexibility"));
//plans.add(new FitnessPlan("Yoga", 210, "advanced", "Improve Flexibility"));
	}

	public static void displayFitnessPlans(Scanner scanner, User user) {

		// Display fitness goal options
		System.out.println("Choose your fitness goal:");
		System.out.println("A. Weight Loss");
		System.out.println("B. Muscle Building");
		System.out.println("C. Improve Flexibility");
		System.out.print("Choose an option: ");
		String goalChoice = scanner.nextLine().toUpperCase();

		String goal;
		switch (goalChoice) {
		case "A":
			goal = "Weight Loss";
			break;
		case "B":
			goal = "Muscle Building";
			break;
		case "C":
			goal = "Improve Flexibility";
			break;
		default:
			System.out.println("Invalid option. Exiting.");
			return; // Exit if invalid option
		}

		// Display fitness level options
		System.out.println("Enter your current fitness level:");
		System.out.println("A. Beginner");
		System.out.println("B. Intermediate");
		System.out.println("C. Advanced");
		System.out.print("Choose an option: ");
		String levelChoice = scanner.nextLine().toUpperCase();

		String level;
		switch (levelChoice) {
		case "A":
			level = "beginner"; // Ensure this matches the case in FitnessPlan
			break;
		case "B":
			level = "intermediate"; // Ensure this matches the case in FitnessPlan
			break;
		case "C":
			level = "advanced"; // Ensure this matches the case in FitnessPlan
			break;
		default:
			System.out.println("Invalid option. Exiting.");
			return; // Exit if invalid option
		}

		// Ask if the user had any surgeries
		System.out.print("Have you had any surgeries? (yes/no): ");
		String surgeryResponse = scanner.nextLine().trim().toLowerCase();
		if (surgeryResponse.equals("yes") || surgeryResponse.equals("y")) {
			System.out.println(
					"Note: Please ensure that any chosen fitness plan is suitable based on your medical history.");
			// return; // Exit if surgeries present
		}

// Ask if the user has any illnesses
		System.out.print("Do you have any illnesses? (yes/no): ");
		String illnessResponse = scanner.nextLine().trim().toLowerCase();
		if (illnessResponse.equals("yes") || illnessResponse.equals("y")) {
			System.out.println("Note: It's better to consult a healthcare provider before starting a fitness plan.");
			// return; // Exit if illnesses present
		}

		// Display fitness plans if no surgeries or illnesses
		System.out.println("\nAvailable Fitness Plans based on your input:");
		boolean foundPlan = false; // Flag to check if any plan is found
		for (FitnessPlan plan : plans) {
			// Check if the plan matches the user's goal and fitness level
			if (plan.getGoal().equalsIgnoreCase(goal) && plan.getLevel().equalsIgnoreCase(level)) {
				System.out.println(plan);
				foundPlan = true; // Set flag if a plan is found
			}
		}

		if (!foundPlan) {
			System.out.println("No fitness plans found that match your criteria.");
		}
	}
}