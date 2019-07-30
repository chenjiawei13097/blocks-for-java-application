package org.firefly.provider.cryptography.des;

import org.firefly.provider.cryptography.CryptographyException;

public interface SymmetricCryptography<K> {
    K generateKey() throws CryptographyException;

    byte[] encrypt(byte[] plainText, K key) throws CryptographyException;

    byte[] decrypt(byte[] cipherText, K key) throws CryptographyException;
}
