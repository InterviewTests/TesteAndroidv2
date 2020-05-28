package br.com.dpassos.bankandroid.infra;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CryptoLoader {

    private static Crypto crypto = new CryptoLoader().loadCrypto();

    public static Crypto getCrypto() {
        return crypto;
    }

    private Crypto loadCrypto() {
        PersistenceContext persistenceContext = new PersistenceContext();
        FileInputStream fis;
        try {
            fis = persistenceContext.openFileReader("crypto");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Crypto crypto = (Crypto) ois.readObject();
            ois.close();
            return crypto;
        } catch (Exception e) {
            e.printStackTrace();
        }

        FileOutputStream fos;
        try {
            Crypto crypto = new Crypto();
            fos = persistenceContext.openFileWritter("crypto");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(crypto);
            oos.close();

            return crypto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
