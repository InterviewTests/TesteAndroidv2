package br.com.dpassos.bankandroid.infra;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/***
 * Simple implementation.
 * Best impl include certify, keystore or third part libs.
 * But we are using a interface to represent this implementation,
 * when new better implementation is available it will override this.
 */
public class Crypto implements Serializable {

    byte[] key;
    public static final String AES = "AES";
    public static final String SHA1PRNG = "SHA1PRNG";

    public Crypto() throws Exception{
        byte[] keyStart = (System.currentTimeMillis() + "_" + new Random().nextInt(1000)).getBytes();
        KeyGenerator kgen = KeyGenerator.getInstance(AES);
        SecureRandom sr = SecureRandom.getInstance(SHA1PRNG);
        sr.setSeed(keyStart);
        kgen.init(256, sr);
        SecretKey skey = kgen.generateKey();
        key = skey.getEncoded();
    }

    public byte[] encrypt(byte[] bytes) throws Exception{
        return encrypt(key,bytes);
    }


    public byte[] decrypt(byte[] bytes) throws Exception{
        return decrypt(key,bytes);
    }

    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }

    private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }
}
