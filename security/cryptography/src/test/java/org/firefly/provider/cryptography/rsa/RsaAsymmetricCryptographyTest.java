package org.firefly.provider.cryptography.rsa;


import org.firefly.provider.cryptography.CryptographyException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertArrayEquals;

public class RsaAsymmetricCryptographyTest {
    private static AsymmetricCryptography<byte[], byte[]> asymmetricCryptography;
    private static byte[] publicKey;
    private static byte[] privateKey;

    @Before
    public void setUp() throws Exception {
        asymmetricCryptography = new RsaAsymmetricCryptography();
        KeyPair<byte[], byte[]> keyPair = asymmetricCryptography.generateKeyPair();
        publicKey = keyPair.getPublicKey();
        privateKey = keyPair.getPrivateKey();
    }

    @After
    public void tearDown() {
        privateKey = null;
        publicKey = null;
        asymmetricCryptography = null;
    }

    @Test
    public void testEncryptWithPublicKey() throws CryptographyException {
        byte[] plainTextSend = "123!&kjWdfYYL?".getBytes(StandardCharsets.UTF_8);

        byte[] cipherText = asymmetricCryptography.encryptWithPublicKey(plainTextSend, publicKey);
        byte[] plainTextReceive = asymmetricCryptography.decryptWithPrivateKey(cipherText, privateKey);

        assertArrayEquals(plainTextSend, plainTextReceive);
    }

    @Test
    public void testEncryptWithPrivateKey() throws CryptographyException {
        byte[] plainTextSend = "3!&jWdfYYL?".getBytes(StandardCharsets.UTF_8);

        byte[] cipherText = asymmetricCryptography.encryptWithPrivateKey(plainTextSend, privateKey);
        byte[] plainTextReceive = asymmetricCryptography.decryptWithPublicKey(cipherText, publicKey);

        assertArrayEquals(plainTextSend, plainTextReceive);
    }

    @Test
    public void testSignAndVerify() throws CryptographyException {
        byte[] message = "fj123!&kWfY".getBytes(StandardCharsets.UTF_8);

        byte[] messageDigest = asymmetricCryptography.sign(message, privateKey);
        boolean verify = asymmetricCryptography.verify(message, messageDigest, publicKey);

        Assert.assertTrue(verify);
    }
}