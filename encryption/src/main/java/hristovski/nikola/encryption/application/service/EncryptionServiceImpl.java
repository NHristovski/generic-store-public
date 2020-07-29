package hristovski.nikola.encryption.application.service;

import hristovski.nikola.encryption.application.exception.EncryptionException;
import lombok.RequiredArgsConstructor;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncryptionServiceImpl implements EncryptionService {

    private final AES256TextEncryptor encryptor;

    @Override
    public String encrypt(String value) throws EncryptionException {
        try {
            return encryptor.encrypt(value);
        } catch (Exception ex) {
            throw new EncryptionException("Failed to encrypt", ex);
        }
    }

    @Override
    public String decrypt(String encryptedValue) throws EncryptionException {
        try {
            return encryptor.decrypt(encryptedValue);
        } catch (Exception ex) {
            throw new EncryptionException("Failed to encrypt", ex);
        }
    }
}
