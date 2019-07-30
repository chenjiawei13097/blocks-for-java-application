package org.firefly.provider.cryptography.des;

import org.apache.commons.codec.binary.Hex;
import org.firefly.provider.cryptography.CryptographyException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class DesSymmetricCryptography implements SymmetricCryptography<byte[]> {
    private static final String DES_ALGORITHM = "DES";
    private static final String NO_DES_ALGORITHM_PROVIDER =
            String.format("No provider which implements the %s algorithm is found", DES_ALGORITHM);

    @Override
    public byte[] generateKey() throws CryptographyException {
        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance(DES_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new CryptographyException(NO_DES_ALGORITHM_PROVIDER, e);
        }

        keyGenerator.init(56);
        SecretKey generateKey = keyGenerator.generateKey();

        return generateKey.getEncoded();
    }

    @Override
    public byte[] encrypt(byte[] plainText, byte[] key) throws CryptographyException {
        byte[] filledData = fillPlainText(plainText);
        return processText(filledData, key, Cipher.ENCRYPT_MODE);
    }

    @Override
    public byte[] decrypt(byte[] cipherText, byte[] key) throws CryptographyException {
        return parsePlainText(processText(cipherText, key, Cipher.DECRYPT_MODE));
    }

    private byte[] processText(byte[] text, byte[] key, int mode) throws CryptographyException {
        SecretKeyFactory secretKeyFactory;
        try {
            secretKeyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new CryptographyException(NO_DES_ALGORITHM_PROVIDER, e);
        }

        SecretKey secretKey;
        try {
            DESKeySpec desKeySpec = new DESKeySpec(key);
            secretKey = secretKeyFactory.generateSecret(desKeySpec);
        } catch (InvalidKeyException | InvalidKeySpecException e) {
            throw new CryptographyException(
                    String.format("Fails to get key from %s", Hex.encodeHexString(key, true)), e);
        }

        Cipher cipher;
        try {
            cipher = Cipher.getInstance(DES_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new CryptographyException(NO_DES_ALGORITHM_PROVIDER, e);
        } catch (NoSuchPaddingException e) {
            throw new CryptographyException(String.format("Getting cipher of %s goes wrong", DES_ALGORITHM), e);
        }

        try {
            cipher.init(mode, secretKey);
            return cipher.doFinal(text);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new CryptographyException("An exception happens when encrypt plain text or decrypt cipher text", e);
        }
    }

    /**
     * Fill data to be times of 8 bytes.
     *
     * @param data plain text
     * @return filled data filling with 1 to 8 bytes, last byte is number of filled bytes
     */
    private byte[] fillPlainText(byte[] data) {
        int dataLength = data.length;
        if (dataLength > Integer.MAX_VALUE - 8) {
            throw new IllegalArgumentException("Plain text to be processed is too big");
        }

        int fillByteNumber = 8 - dataLength % 8;
        byte[] fillBytes = new byte[fillByteNumber];
        fillBytes[fillByteNumber - 1] = (byte) fillByteNumber;

        byte[] filledData = new byte[dataLength + fillByteNumber];
        System.arraycopy(data, 0, filledData, 0, dataLength);
        System.arraycopy(fillBytes, 0, filledData, dataLength, fillByteNumber);
        return filledData;
    }

    /**
     * Parse data from filled plant text.
     *
     * @param data filled plain text
     * @return original plain text
     */
    private byte[] parsePlainText(byte[] data) {
        int dataLength = data.length;

        int fillByteNumber = data[dataLength - 1];
        byte[] originalData = new byte[dataLength - fillByteNumber];
        System.arraycopy(data, 0, originalData, 0, originalData.length);
        return originalData;
    }
}
