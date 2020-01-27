package com.klobbix.gaming.account;

import com.klobbix.database.secure.SecurePassword;

public class Account {

    private String username;
    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = SecurePassword.encrypt(password);
    }

    public String getUsername() {
        return username;
    }

    public boolean verifyPassword(String plain) {
        return SecurePassword.matches(plain, password);
    }
}
