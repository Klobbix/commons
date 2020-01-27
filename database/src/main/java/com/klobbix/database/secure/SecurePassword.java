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
		return strongDigest(input);
	}


	/**
	 * Generates a digested password using a specified algorithm.
	 *
	 * @param input     The password to encrypt
	 * @param algorithm The hashing algorithm to use
	 * @return The encrypted password
	 */
	public static String encrypt(String input, String algorithm) {
		ConfigurablePasswordEncryptor encryptor = new ConfigurablePasswordEncryptor();
		encryptor.setAlgorithm(algorithm);
		encryptor.setPlainDigest(true);
		return encryptor.encryptPassword(input);
	}

	public static boolean matches(String plain, String encrypted) {
		return matchesStrongDigest(plain, encrypted);
	}

	/**
	 * Verifies that an input matches an encrypted password.
	 *
	 * @param input     The input
	 * @param encrypted The encrypted password
	 * @param algorithm The algorithm used in the original encryption
	 * @return True if matches otherwise false
	 */
	public static boolean matches(String input, String encrypted, String algorithm) {
		ConfigurablePasswordEncryptor encryptor = new ConfigurablePasswordEncryptor();
		encryptor.setAlgorithm(algorithm);
		encryptor.setPlainDigest(true);
		return encryptor.checkPassword(input, encrypted);
	}

	private static String strongDigest(String input) {
		return new StrongPasswordEncryptor().encryptPassword(input);
	}

	private static boolean matchesStrongDigest(String input, String encrypted) {
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		return encryptor.checkPassword(input, encrypted);
	}
}
