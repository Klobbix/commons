package com.klobbix.database.secure;

import org.jasypt.util.text.AES256TextEncryptor;

/**
 * Provides two-way encryption for a String using the AES256 Algorithm.
 */
public class SecureText implements SecureEncryption<String> {

	private final AES256TextEncryptor encryptor = new AES256TextEncryptor();

	public SecureText(String password) {
		encryptor.setPassword(password);
	}

	@Override
	public String encrypt(String input) {
		return encryptor.encrypt(input);
	}

	@Override
	public String decrypt(String encrypted) {
		return encryptor.decrypt(encrypted);
	}

	public AES256TextEncryptor getEncryptor() {
		return encryptor;
	}
}
