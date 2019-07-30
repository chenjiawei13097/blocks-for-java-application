package org.firefly.provider.cryptography;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.firefly.provider.cryptography.des.DesSymmetricCryptography;
import org.firefly.provider.cryptography.des.SymmetricCryptography;
import org.firefly.provider.cryptography.rsa.AsymmetricCryptography;
import org.firefly.provider.cryptography.rsa.KeyPair;
import org.firefly.provider.cryptography.rsa.RsaAsymmetricCryptography;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class CryptographyDemo {
    private static AsymmetricCryptography<byte[], byte[]> asymmetricCryptography = new RsaAsymmetricCryptography();
    private static SymmetricCryptography<byte[]> symmetricCryptography = new DesSymmetricCryptography();

    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            String operate;
            do {
                System.out.println("Chose an operation:\n" +
                        "1) generates a key pair.\n" +
                        "2) encrypts a file.\n" +
                        "3) decrypts a file.");
                operate = bufferedReader.readLine();
            } while (!"1".equals(operate) && !"2".equals(operate) && !"3".equals(operate));

            if ("1".equals(operate)) {
                generateKeyPair(bufferedReader);
            } else if ("2".equals(operate)) {
                encryptFile(bufferedReader);
            } else {
                decryptFile(bufferedReader);
            }
        } catch (IOException | CryptographyException | DecoderException e) {
            e.printStackTrace();
        }
    }

    private static void generateKeyPair(BufferedReader bufferedReader) throws IOException, CryptographyException {
        System.out.println("Absolute path of file to save generated key pair:");
        BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(new File(bufferedReader.readLine()))));

        KeyPair<byte[], byte[]> keyPair = asymmetricCryptography.generateKeyPair();
        String publicKey = Hex.encodeHexString(keyPair.getPublicKey(), true);
        String privateKey = Hex.encodeHexString(keyPair.getPrivateKey(), true);

        bufferedWriter.write("public:" + publicKey);
        bufferedWriter.newLine();
        bufferedWriter.write("private:" + privateKey);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private static void encryptFile(
            BufferedReader bufferedReader) throws IOException, CryptographyException, DecoderException {
        System.out.println("Absolute path of source file:");
        String source = bufferedReader.readLine();

        System.out.println("Absolute path of target file:");
        String target = bufferedReader.readLine();

        System.out.println("Public key:");
        String publicKey = bufferedReader.readLine();

        encryptFile(new File(source), new File(target), Hex.decodeHex(publicKey));
    }

    private static void decryptFile(
            BufferedReader bufferedReader) throws IOException, CryptographyException, DecoderException {
        System.out.println("Absolute path of source file:");
        String source = bufferedReader.readLine();

        System.out.println("Absolute path of target file:");
        String target = bufferedReader.readLine();

        System.out.println("Private key:");
        String privateKey = bufferedReader.readLine();

        decryptFile(new File(source), new File(target), Hex.decodeHex(privateKey));
    }

    private static void encryptFile(
            File source, File target, byte[] publicKey) throws IOException, CryptographyException {
        byte[] plainDesKey = symmetricCryptography.generateKey();
        byte[] cipherDesKey = asymmetricCryptography.encryptWithPublicKey(plainDesKey, publicKey);

        InputStream inputStream = new FileInputStream(source);
        OutputStream outputStream = new FileOutputStream(target);

        outputStream.write(toBytes(cipherDesKey.length));
        outputStream.write(cipherDesKey);

        byte[] bytes = new byte[2048];
        int readLen;
        do {
            readLen = inputStream.read(bytes);
            if (readLen != -1) {
                byte[] tmp = new byte[readLen];
                System.arraycopy(bytes, 0, tmp, 0, readLen);
                byte[] encrypt = symmetricCryptography.encrypt(tmp, plainDesKey);
                outputStream.write(toBytes(encrypt.length));
                outputStream.write(encrypt);
            }
        } while (readLen != -1);

        outputStream.close();
        inputStream.close();
    }

    private static byte[] toBytes(int length) {
        byte[] lengthInBytes = new byte[4];
        lengthInBytes[0] = (byte) ((length >> 8 >> 8 >> 8) & 0xff);
        lengthInBytes[1] = (byte) ((length >> 8 >> 8) & 0xff);
        lengthInBytes[2] = (byte) ((length >> 8) & 0xff);
        lengthInBytes[3] = (byte) ((length) & 0xff);
        return lengthInBytes;
    }

    private static void decryptFile(
            File source, File target, byte[] privateKey) throws IOException, CryptographyException {
        InputStream inputStream = new FileInputStream(source);
        OutputStream outputStream = new FileOutputStream(target);

        int cipherDesKeyLength = readLength(inputStream);
        byte[] cipherDesKey = new byte[cipherDesKeyLength];
        if (inputStream.read(cipherDesKey) != cipherDesKeyLength) {
            throw new IOException("Reading des key fails");
        }

        byte[] plainDesKey = asymmetricCryptography.decryptWithPrivateKey(cipherDesKey, privateKey);

        int fragmentLength;
        do {
            fragmentLength = readLength(inputStream);
            if (fragmentLength != -1) {
                byte[] fragment = new byte[fragmentLength];
                if (inputStream.read(fragment) != fragmentLength) {
                    throw new IOException("Reading data fails");
                }
                outputStream.write(symmetricCryptography.decrypt(fragment, plainDesKey));
            }
        } while (fragmentLength != -1);

        outputStream.close();
        inputStream.close();
    }

    private static int readLength(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[4];
        if (inputStream.read(bytes) == 4) {
            return new BigInteger(1, bytes).intValue();
        }
        return -1;
    }
}
