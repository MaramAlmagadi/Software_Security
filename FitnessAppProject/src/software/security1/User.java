package software.security1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class User implements Serializable {
	private final String username;
	private final String email;
	private final String password;

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public static boolean signUp(String username, String email, String password, int age) {
		if (age < 16) {
			System.out.println("You must be at least 16 years old to sign up.");
			return false;
		}

		if (username.length() < 2) {
			System.out.println("Username must be at least 2 characters.");
			return false;
		}

		if (password.length() < 6) {
			System.out.println("Password must be at least 6 characters.");
			return false;
		}

		// Encrypt the email and hash the password
		String encryptedEmail = EncryptionUtil.encrypt(email);
		String hashedPassword = IntegrityUtil.hashPassword(password);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
			// Save the username, encrypted email, and hashed password
			writer.write(username + "," + encryptedEmail + "," + hashedPassword + "\n");
			System.out.println("Sign-up successful!");
			return true;
		} catch (IOException e) {
			System.out.println("Error writing to file.");
			return false;
		}
	}

	public static User login(String email, String password) {
		try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length < 3) {
					continue; // Skip invalid entries
				}

				String decryptedEmail;
				try {
					decryptedEmail = EncryptionUtil.decrypt(parts[1]);
					// Check if the decrypted email matches the input email
					if (!decryptedEmail.equals(email)) {
						continue; // Skip if emails don't match
					}
				} catch (Exception e) {
					continue; // Skip if there's a decryption error
				}

				// Hash the input password and compare with the stored hashed password
				String hashedInputPassword = IntegrityUtil.hashPassword(password);
				if (hashedInputPassword.equals(parts[2])) {
					System.out.println("Login successful!");
					return new User(parts[0], email, parts[2]); // Return user if successful
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading from file.");
		}

		System.out.println("Invalid email or password.");
		return null;
	}

	private static final long serialVersionUID = 1L;
}
