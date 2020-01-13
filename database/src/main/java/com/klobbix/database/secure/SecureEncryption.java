package com.klobbix.database.secure;

public interface SecureEncryption<T> {

	T encrypt(T input);

	T decrypt(T encrypted);
}
