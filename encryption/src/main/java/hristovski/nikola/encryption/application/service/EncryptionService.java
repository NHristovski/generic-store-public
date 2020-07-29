package hristovski.nikola.encryption.application.service;

import hristovski.nikola.encryption.application.exception.EncryptionException;

public interface EncryptionService {
    String encrypt(final String value) throws EncryptionException;
    String decrypt(final String encryptedValue) throws EncryptionException;
}
