package software.security1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class IntegrityUtil {

	public static String hashPassword(String password) {
		try {
			// Create a MessageDigest instance for SHA-256
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = digest.digest(password.getBytes());

			// Convert the byte array to a hexadecimal string
			StringBuilder hexString = new StringBuilder();
			for (byte b : hashBytes) {
				hexString.append(String.format("%02x", b));
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error hashing password", e);
		}
	}
}
