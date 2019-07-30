package org.firefly.provider.cryptography.des;

import org.firefly.provider.cryptography.CryptographyException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertArrayEquals;

public class DesSymmetricCryptographyTest {
    private static SymmetricCryptography<byte[]> symmetricCryptography;
    private static byte[] key;

    @Before
    public void setUp() throws Exception {
        symmetricCryptography = new DesSymmetricCryptography();
        key = symmetricCryptography.generateKey();
    }

    @After
    public void tearDown() {
        key = null;
        symmetricCryptography = null;
    }

    @Test
    public void testEncrypt() throws CryptographyException {
        byte[] plainTextSend = "123!&kjWdfYYL?".getBytes(StandardCharsets.UTF_8);

        byte[] cipherText = symmetricCryptography.encrypt(plainTextSend, key);
        byte[] plainTextReceive = symmetricCryptography.decrypt(cipherText, key);

        assertArrayEquals(plainTextSend, plainTextReceive);
    }
}