package com.klobbix.database.secure;

import org.jasypt.properties.EncryptableProperties;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Reads a properties file that has encrypted values. Encrypted values are surrounded by ENC(VALUE).
 * An example property looks like the following:
 * datasource.password=ENC(G6N718UuyPE5bHyWKyuLQSm02auQPUtm)
 */
public class SecurePropertiesReader {

    private final SecureText secureText;
    private final Properties properties;

    public SecurePropertiesReader(String path, String password) {
        secureText = new SecureText(password);
        properties = loadPropertiesFile(path);
    }

    public Properties getProperties() {
        return properties;
    }

    private Properties loadPropertiesFile(String path) {
        Properties properties = new EncryptableProperties(secureText.getEncryptor());
        try {
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }

}
