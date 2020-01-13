package com.klobbix.database.secure;

import org.jasypt.util.numeric.AES256IntegerNumberEncryptor;

import java.math.BigInteger;

/**
 * Provides two-way encryption for a BigInteger using the AES256 Algorithm.
 */
public class SecureBigInteger implements SecureEncryption<BigInteger> {

	private final AES256IntegerNumberEncryptor encryptor = new AES256IntegerNumberEncryptor();

	public SecureBigInteger(String password) {
		encryptor.setPassword(password);
	}

	@Override
	public BigInteger encrypt(BigInteger input) {
		return encryptor.encrypt(input);
	}

	@Override
	public BigInteger decrypt(BigInteger encrypted) {
		return encryptor.decrypt(encrypted);
	}
}
