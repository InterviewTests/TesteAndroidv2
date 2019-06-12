package br.com.douglas.fukuhara.bank.persistance;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import br.com.douglas.fukuhara.bank.BuildConfig;

public class LocalStoragePreM extends LocalStorage {

    private static SharedPreferences mPreference;
    private final Cipher mCipher;
    private final SecretKeySpec mKeySpec;
    private final IvParameterSpec mIvSpec;

    public LocalStoragePreM(Context context) {
        mPreference = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        try {
            long salt = BuildConfig.SALT_VALUE;

            PBEKeySpec pbeKeySpec = new PBEKeySpec(BuildConfig.PBE_PASS.toCharArray(), String.valueOf(salt).getBytes(), 1324, 256);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] secretKey = secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();

            mKeySpec = new SecretKeySpec(secretKey, "AES");
            mCipher = Cipher.getInstance("AES/CBC/ZeroBytePadding");
            mIvSpec = new IvParameterSpec(BuildConfig.IV_SPEC.getBytes());
            mCipher.init(Cipher.ENCRYPT_MODE, mKeySpec, mIvSpec);

            return;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Falha ao inicializar a chave de criptografia (pre Android M)");
    }

    @Override
    public void saveLogin(String login) {
        try {
            mCipher.init(Cipher.ENCRYPT_MODE, mKeySpec, mIvSpec);
            byte[] loginKeyEncryptedBytes = mCipher.doFinal(SHARED_PREF_LOGIN_KEY.getBytes());
            byte[] loginValueEncryptedBytes = mCipher.doFinal(login.getBytes());

            String wrappedLoginKey = Base64.encodeToString(loginKeyEncryptedBytes, Base64.NO_WRAP| Base64.DEFAULT);
            String wrappedLoginValue = Base64.encodeToString(loginValueEncryptedBytes, Base64.NO_WRAP| Base64.DEFAULT);

            SharedPreferences.Editor editor = mPreference.edit();
            editor.putString(wrappedLoginKey, wrappedLoginValue);
            editor.apply();

        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getLogin() {
        String returnValue = "";
        try {
            mCipher.init(Cipher.ENCRYPT_MODE, mKeySpec, mIvSpec);
            String wrappedLoginKey = Base64.encodeToString(mCipher.doFinal(SHARED_PREF_LOGIN_KEY.getBytes()), Base64.NO_WRAP|Base64.DEFAULT);
            String encryptedLoginValueFromPref = mPreference.getString(wrappedLoginKey, "");

            byte[] unwrapLoginValue = Base64.decode(encryptedLoginValueFromPref, Base64.DEFAULT);
            if (!encryptedLoginValueFromPref.isEmpty()) {
                mCipher.init(Cipher.DECRYPT_MODE, mKeySpec, mIvSpec);
                byte[] decodedLoginValueInBytes = mCipher.doFinal(unwrapLoginValue);
                returnValue = new String(decodedLoginValueInBytes);
            }
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return returnValue;
    }
}
