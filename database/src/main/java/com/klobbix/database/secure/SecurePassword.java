package com.klobbix.database.secure;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 * Provides a one way encryption of passwords using random salt bytes and multiple iterations.
 */
public class SecurePassword {

	/**
	 * Encrypts a password using strong hashing.
	 *
	 * @param input The password to encrypt
	 * @return The digested password
	 */
	public static String encrypt(String input) {
		return encrypt(input, true);
	}

	/**
	 * Generates a encrypted password.
	 *
	 * @param input  The password to encrypt
	 * @param strong Makes the hash more secure with multiple hashing and random salt bytes
	 * @return The encrypted password
	 */
	public static String encrypt(String input, boolean strong) {
		return strong ? strongDigest(input) : digest(input);
	}

	/**
	 * Generates a digested password using a specified algorithm.
	 *
	 * @param input     The password to encrypt
	 * @param algorithm The hashing algorithm to use
	 * @param strong    Makes the hash more secure with multiple hashing and random salt bytes
	 * @return The encrypted password
	 */
	public static String encrypt(String input, String algorithm, boolean strong) {
		ConfigurablePasswordEncryptor encryptor = new ConfigurablePasswordEncryptor();
		encryptor.setAlgorithm(algorithm);
		encryptor.setPlainDigest(strong);
		return encryptor.encryptPassword(input);
	}

	public static boolean matches(String input) {
		return matchesStrongDigest(input);
	}

	/**
	 * Verifies that an input matches an encrypted password.
	 *
	 * @param input  The input
	 * @param strong If the original encryption algorithm was strong
	 * @return True if matches otherwise false
	 */
	public static boolean matches(String input, boolean strong) {
		return strong ? matchesStrongDigest(input) : matchesDigest(input);
	}

	/**
	 * Verifies that an input matches an encrypted password.
	 *
	 * @param input     The input
	 * @param algorithm The algorithm used in the original encryption
	 * @param strong    If the original encryption algorithm was strong
	 * @return True if matches otherwise false
	 */
	public static boolean matches(String input, String algorithm, boolean strong) {
		ConfigurablePasswordEncryptor encryptor = new ConfigurablePasswordEncryptor();
		encryptor.setAlgorithm(algorithm);
		encryptor.setPlainDigest(strong);
		return encryptor.checkPassword(input, encryptor.encryptPassword(input));
	}

	private static String digest(String input) {
		return new BasicPasswordEncryptor().encryptPassword(input);
	}

	private static String strongDigest(String input) {
		return new StrongPasswordEncryptor().encryptPassword(input);
	}

	private static boolean matchesDigest(String input) {
		BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
		return encryptor.checkPassword(input, encryptor.encryptPassword(input));
	}

	private static boolean matchesStrongDigest(String input) {
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		return encryptor.checkPassword(input, encryptor.encryptPassword(input));
	}
}
