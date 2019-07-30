package org.firefly.provider.cryptography.rsa;

import org.firefly.provider.cryptography.CryptographyException;

public interface AsymmetricCryptography<P, V> {
    KeyPair<P, V> generateKeyPair() throws CryptographyException;

    byte[] encryptWithPublicKey(byte[] plainText, P publicKey) throws CryptographyException;

    byte[] encryptWithPrivateKey(byte[] plainText, V privateKey) throws CryptographyException;

    byte[] decryptWithPublicKey(byte[] cipherText, P publicKey) throws CryptographyException;

    byte[] decryptWithPrivateKey(byte[] cipherText, V privateKey) throws CryptographyException;

    byte[] sign(byte[] message, V privateKey) throws CryptographyException;

    boolean verify(byte[] message, byte[] messageDigest, P publicKey) throws CryptographyException;
}
