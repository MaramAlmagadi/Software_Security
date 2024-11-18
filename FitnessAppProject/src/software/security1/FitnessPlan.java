package software.security1;

import java.io.Serializable;

public class FitnessPlan implements Serializable {
	// private final String name;
	private final String category;
	private final int minDuration;
	private final String fitnessLevel;
	private final String healthGoal;

	public FitnessPlan(String category, int minDuration, String fitnessLevel, String healthGoal) {
		// this.name = name;
		this.category = category;
		this.minDuration = minDuration;
		this.fitnessLevel = fitnessLevel;
		this.healthGoal = healthGoal;
	}

	public String getGoal() {
		return healthGoal;
	}

	public String getLevel() {
		return fitnessLevel;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Category: " + category + ", Minimum Duration: " + minDuration + " mins, Level: " + fitnessLevel
				+ ", Goal: " + healthGoal;
	}

}