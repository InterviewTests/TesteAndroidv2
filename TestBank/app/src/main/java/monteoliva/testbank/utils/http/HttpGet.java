package monteoliva.testbank.utils.http;

// imports API JAVA
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Http GET
 *
 * @author Claudio Monteoliva
 * @version 1.0
 * @copyright 2019 Monteoliva Developer
 *
 */
public class HttpGet {
    private List<HttpParams> params;
    private List<HttpParams> paramsH;

    // seta o DEFAULT CHARSET
    private String DEFAULT_CHARSET = "UTF-8";

    /**
     * Constructor
     */
    public HttpGet() {
        // inicia array
        params  = new ArrayList<>();
        paramsH = new ArrayList<>();
    }

    /**
     * Metodo que acrescenta os parametros para a URL
     *
     * @param name
     * @param value
     */
    public void add(final String name, final String value) {
        final HttpParams tabela = new HttpParams();
                         tabela.setNome(name);
                         tabela.setValor(value);
        // adiciona
        params.add(tabela);
    }

    /**
     * Metodo que acrescenta os Parametros Header para a URL
     *
     * @param name
     * @param value
     */
    public void addHeader(final String name, final String value) {
        final HttpParams tabela = new HttpParams();
                         tabela.setNome(name);
                         tabela.setValor(value);
        // adiciona
        paramsH.add(tabela);
    }

    /**
     * Metodo que executa a chama Http
     *
     * @param urlToRead
     * @return
     */
    public HttpResultBean send(String urlToRead) {
        // initialize http result
        HttpResultBean result = null;

        // buffer
        BufferedReader rd;

        try {
            // get variables
            String line = "";
            String urlParameters = "";

            // verifica os params
            for (HttpParams param : params) {
                // pega a linha
                final String linha = param.getNome() + "=" + param.getValor();

                // verifica
                if (urlParameters.length() > 0) { urlParameters += "&" + linha; }
                else                            { urlParameters += "?" + linha; }
            }

            // incrementa a URL
            if (!urlParameters.isEmpty()) { urlToRead += urlParameters; }

            // seta a URL
            URL url = new URL(urlToRead);

            // realiza a conexao
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                              conn.setRequestMethod("GET");
                              conn.setDoInput(true);
                              conn.setUseCaches(false);

            // verifica os headers
            for (HttpParams paramH : paramsH) {
                // seta Header
                conn.setRequestProperty(paramH.getNome(), paramH.getValor());
            }

            // initialize InputStream
            InputStream input;

            // status response
            final int codeStatus = conn.getResponseCode();

            // verify status
            if ((codeStatus == HttpURLConnection.HTTP_OK) ||
                (codeStatus == HttpURLConnection.HTTP_CREATED)) {
                // get InputStream
                input = conn.getInputStream();
            }
            else {
                input = conn.getErrorStream();
            }

            // seta o BufferReader
            rd = new BufferedReader(new InputStreamReader(input, DEFAULT_CHARSET));

            // message
            String message = "";

            // percorre o arquivo e retorna o result
            while ((line = rd.readLine()) != null) { message += line; }

            // set Result
            result = new HttpResultBean();
            result.setCode(codeStatus);
            result.setMessage(message);

            // fecha o BufferedReader
            rd.close();
            conn.disconnect();
        }
        catch (MalformedURLException me) {}
        catch (UnsupportedEncodingException ue) {}
        catch (IOException                  ie) {}

        // retorna
        return result;
    }

    /**
     * Metodo Setter's
     *
     * @param CHARSET
     */
    public void setCharSet(String CHARSET) { this.DEFAULT_CHARSET = CHARSET; }
}