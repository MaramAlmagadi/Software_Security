package software.security1;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {
	private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
	private static final String SECRET_KEY = "1234567890123456"; // 16-byte key for AES

	private static SecretKeySpec secretKey;

	static {
		try {
			// Initialize the secret key with AES algorithm and 16-byte key
			secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
		} catch (Exception e) {
			throw new RuntimeException("Error initializing secret key", e);
		}
	}

	/**
	 * Encrypts a plaintext string using AES algorithm with Base64 encoding.
	 * 
	 * @param data The plaintext to encrypt.
	 * @return The encrypted text as a Base64 encoded string.
	 */
	public static String encrypt(String data) {
		try {
			// Initialize cipher with AES algorithm in ECB mode with PKCS5Padding
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);

			// Perform encryption
			byte[] encryptedData = cipher.doFinal(data.getBytes());

			// Encode encrypted bytes to Base64 string for storage
			return Base64.getEncoder().encodeToString(encryptedData);
		} catch (Exception e) {
			throw new RuntimeException("Error encrypting data", e);
		}
	}

	/**
	 * Decrypts an AES-encrypted, Base64-encoded string back to plaintext.
	 * 
	 * @param encryptedData The Base64 encoded string to decrypt.
	 * @return The decrypted plaintext string.
	 */
	public static String decrypt(String encryptedData) {
		try {
			// Initialize cipher with AES algorithm in ECB mode with PKCS5Padding
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);

			// Decode Base64 encoded data
			byte[] decodedData = Base64.getDecoder().decode(encryptedData);

			// Perform decryption
			return new String(cipher.doFinal(decodedData));
		} catch (Exception e) {
			throw new RuntimeException("Error decrypting data", e);
		}
	}
}
