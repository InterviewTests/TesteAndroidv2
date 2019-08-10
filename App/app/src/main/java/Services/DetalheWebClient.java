package Services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import Padrões.ValoresPadrao;

public class DetalheWebClient {
    public String get(int id){
        try {
            URL url = new URL(ValoresPadrao.URL_DETALHES + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            Scanner scanner = new Scanner(connection.getInputStream());
            String resposta = scanner.nextLine();
            while (scanner.hasNext()){
                resposta += scanner.nextLine();
            }
            scanner.close();
            return resposta;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
