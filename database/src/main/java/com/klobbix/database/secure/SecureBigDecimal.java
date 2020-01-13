package com.klobbix.database.secure;

import org.jasypt.util.numeric.AES256DecimalNumberEncryptor;

import java.math.BigDecimal;

/**
 * Provides two-way encryption for a BigDecimal using the AES256 Algorithm.
 */
public class SecureBigDecimal implements SecureEncryption<BigDecimal> {

	private final AES256DecimalNumberEncryptor encryptor = new AES256DecimalNumberEncryptor();

	public SecureBigDecimal(String password) {
		encryptor.setPassword(password);
	}

	@Override
	public BigDecimal encrypt(BigDecimal input) {
		return encryptor.encrypt(input);
	}

	@Override
	public BigDecimal decrypt(BigDecimal encrypted) {
		return encryptor.decrypt(encrypted);
	}
}
