package com.klobbix.database.secure;

import org.jasypt.util.binary.AES256BinaryEncryptor;

/**
 * Provides two-way encryption for a byte array using the AES256 Algorithm.
 */
public class SecureBinary implements SecureEncryption<byte[]> {

	private final AES256BinaryEncryptor encryptor = new AES256BinaryEncryptor();

	public SecureBinary(String password) {
		encryptor.setPassword(password);
	}

	@Override
	public byte[] encrypt(byte[] input) {
		return encryptor.encrypt(input);
	}

	@Override
	public byte[] decrypt(byte[] encrypted) {
		return encryptor.decrypt(encrypted);
	}
}
